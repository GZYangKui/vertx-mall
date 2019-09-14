package cn.navigational.impl;

import cn.navigational.base.BaseVerticle;
import cn.navigational.enums.EventBusDataType;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.ResponseUtils.*;

public class RestVerticle extends BaseVerticle {

    protected Logger logger = LogManager.getLogger();

    /**
     * 将请求信息转发到对应的子路由上去
     *
     * @param rcx http请求上下文
     */
    protected void sendMessage(RoutingContext rcx) {
        final JsonObject msg = rcx.getBodyAsJson();
        final String requestAPi = msg.getString(EVENT_ADDRESS);
        vertx.eventBus().<JsonObject>request(requestAPi, msg, _rs -> {
            if (_rs.failed()) {
                response(rcx, executeException(_rs.cause()));
                return;
            }
            response(rcx, _rs.result().body());
        });
    }

    /**
     * 拦截http异常状态码,根据http状态码自定义回复信息#目前只处理500/404
     *
     * @param router http请求路由
     */
    protected void exHandler(Router router) {
        router.errorHandler(500, _routingContext -> {
            _routingContext.failure().printStackTrace();
            response(_routingContext, executeException(_routingContext.failure()));
        });

        router.errorHandler(404, _routingContext -> {
            final JsonObject info = responseTemplate("你访问的资源去了火星", 404, false, EventBusDataType.JSON);
            response(_routingContext, info);
        });
    }

    private JsonObject executeException(Throwable _t) {
        logger.error("Event bus 请求失败:{}", nullableStr(_t));
        return responseFailed(ERROR_MESSAGE,500);

    }

}
