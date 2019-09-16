package cn.navigational.impl;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.base.BaseVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.RequestMappingModel;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

/**
 * 用eventBus和通过注解反射方式来执行特定的方法
 *
 * @author GZYangKui
 * @see io.vertx.core.eventbus.EventBus
 * @see RouterMapping
 */
public abstract class RouterVerticle extends BaseVerticle {
    //缓存方法
    private final List<RequestMappingModel> cacheRequest = new ArrayList<>();

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        final Router router = this.getClass().getAnnotation(Router.class);
        if (Objects.isNull(router)) {
            startPromise.fail("Extends RouterVerticle must mark @Router annotation");
            return;
        }
        final Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            final Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == RouterMapping.class) {
                    RequestMappingModel model = new RequestMappingModel();
                    model.setApi(router.api() + ((RouterMapping) annotation).api());
                    model.setHttpRequestMethod(((RouterMapping) annotation).method());
                    model.setMethod(method);
                    cacheRequest.add(model);
                }
            }
        }
        vertx.eventBus().<JsonObject>consumer(getAPi(), msg -> {
            final JsonObject data = msg.body();

            final EBRequest ebRequest = EBRequest.create(data);

            final String path = ebRequest.getPath();
            final HttpMethod httpMethod = ebRequest.getMethod();

            RequestMappingModel request = null;
            for (RequestMappingModel model : cacheRequest) {
                if (model.getApi().equals(path) && model.getHttpRequestMethod() == httpMethod) {
                    request = model;
                    break;
                }
            }
            if (request == null) {
                msg.reply(notFound(path));
                return;
            }
            //动态代理执行方法
            try {

                final Promise<JsonObject> promise = Promise.promise();

                request.getMethod().invoke(this, ebRequest, promise);

                promise.future().setHandler(ar -> {
                    if (ar.failed()) {
                        logger.error("业务逻辑处理失败:{}", nullableStr(ar.cause()));
                        msg.reply(error());
                        return;
                    }
                    msg.reply(ar.result());
                });
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("反射执行：{}方法失败：{}", request.getMethod().getName(), nullableStr(e));
                msg.reply(error());
            }
        });
        startPromise.complete();
    }

    private JsonObject notFound(final String action) {
        final JsonObject msg = responseFailed("处理程序未定义", 404);
        msg.put(EVENT_ADDRESS, action);
        return msg;
    }

    private JsonObject error() {
        return responseFailed(ERROR_MESSAGE, 500);
    }

    private String getAPi() {
        final Router router = this.getClass().getDeclaredAnnotation(Router.class);
        if (Objects.nonNull(router)) {
            return router.api();
        } else {
            return "";
        }
    }

    protected <T> void futureStatus(Future<T> future, Promise<JsonObject> promise) {
        future.setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            T t = future.result();
            if (t instanceof JsonObject && ((JsonObject) t).containsKey(CODE)) {
                promise.complete((JsonObject) t);
            } else {
                promise.complete(responseSuccessJson(future.result()));
            }
        });
    }
}
