package cn.navigational.impl;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.base.BaseVerticle;
import cn.navigational.model.EBRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ResponseUtils.responseFailed;

/**
 * 用eventBus和通过注解发射方式来执行特定的方法
 *
 * @author GZYangKui
 * @see io.vertx.core.eventbus.EventBus
 * @see cn.navigational.annotation.RequestMapping
 */
public abstract class RouterVerticle extends BaseVerticle {
    //缓存方法
    private final Map<String, Map<String, Object>> requestMapping = new HashMap<>();

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
                if (annotation.annotationType() == RequestMapping.class) {
                    final RequestMapping map = (RequestMapping) annotation;
                    final Map<String, Object> info = new HashMap<>();
                    info.put(HTTP_METHOD, ((RequestMapping) annotation).method().toString());
                    info.put("execute", method);
                    requestMapping.put(router.api() + map.api(), info);
                }
            }
        }
        vertx.eventBus().<JsonObject>consumer(getAPi(), _msg -> {
            final JsonObject data = _msg.body();

            EBRequest ebRequest = EBRequest.create(data);

            final String path = data.getString(PATH);
            final String httpMethod = data.getString(HTTP_METHOD);
            //检查当前api是否存在,以及请求方法是否符合@RequestMapping中设定的请求方法
            if (!requestMapping.containsKey(path) || !requestMapping.get(path).get(HTTP_METHOD).equals(httpMethod)) {
                _msg.reply(notFound(path));
                return;
            }
            //反射执行特定方法
            try {
                final Method method = (Method) requestMapping.get(path).get("execute");
                ((Future<JsonObject>) method.invoke(this, data)).setHandler(_rs -> {
                    if (_rs.failed()) {
                        logger.error("业务逻辑处理失败:{}", _rs.cause().getMessage());
                        _msg.reply(error(_rs.cause()));
                        return;
                    }
                    _msg.reply(_rs.result());
                });
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("reflect execute failed cause:{}", e.getCause().getMessage());
                _msg.reply(error(e.getCause()));
            }
        });
        startPromise.complete();
    }

    private JsonObject notFound(final String action) {
        final JsonObject msg = responseFailed("处理程序未定义", 404);
        msg.put(EVENT_ADDRESS, action);
        return msg;
    }

    private JsonObject error(Throwable _t) {
        final JsonObject msg = responseFailed("程序出错", 500);
        msg.put(CAUSE, Objects.isNull(_t.getMessage()) ? "NULL" : _t.getMessage());
        return msg;
    }

    private String getAPi() {
        final Router router = this.getClass().getDeclaredAnnotation(Router.class);
        if (Objects.nonNull(router)) {
            return router.api();
        } else {
            return "";
        }
    }
}
