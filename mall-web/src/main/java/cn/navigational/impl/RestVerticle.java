package cn.navigational.impl;

import cn.navigational.base.BaseVerticle;
import cn.navigational.base.HttpValidator;
import cn.navigational.enums.EventBusDataType;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ResponseUtils.response;
import static cn.navigational.utils.ResponseUtils.responseTemplate;

public class RestVerticle extends BaseVerticle {

    protected void sendMessage(RoutingContext rcx) {
        final JsonObject msg = rcx.getBodyAsJson();
        final String requestAPi = msg.getString(REQUEST_API);
        vertx.eventBus().<JsonObject>request(requestAPi, msg, _rs -> {
            final HttpServerResponse response = rcx.response();

            if (_rs.failed()) {
                response(rcx, executeException(_rs.cause()));
                return;
            }
            response(rcx, _rs.result().body());
        });

    }

    /**
     * 批量注册api接口
     *
     * @param router
     */
    protected void buildApi(Router router) {
        final JsonArray api = (JsonArray) config().remove(API);
        api.stream().map(_r -> (JsonObject) _r).forEach(_r -> {
            final String method = _r.getString(HTTP_METHOD);
            final String path = _r.getString(API);
            final JsonArray validator = _r.getJsonArray("validator", new JsonArray());
            final String comment = _r.getString("comment");
            final Route route;
            if (method.equals("GET")) {
                route = router.get(path);
            } else if (method.equals("POST")) {
                route = router.post(path);
            } else {
                return;
            }
            validator.forEach(_v -> {
                final String v = _v.toString();
                try {
                    HttpValidator vv = (HttpValidator) Class.forName(v).getConstructor().newInstance();
                    route.handler(vv);
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    logger.error("build api failed:{}", e.getCause().getMessage());
                }
            });
            route.handler(this::sendMessage);
            logger.info("({}){}({}) register success!", method, path, comment);
        });
        logger.info("Build api success!");
    }

    protected JsonObject executeException(Throwable _t) {
        final JsonObject msg = responseTemplate("服务器错误", 500, false, EventBusDataType.JSON);
        msg.put(CAUSE, Objects.isNull(_t.getMessage()) ? "NULL" : _t.getMessage());
        return msg;
    }

    protected JsonObject http404() {
        return responseTemplate("你访问的资源去了火星", 404, false, EventBusDataType.JSON);
    }

}
