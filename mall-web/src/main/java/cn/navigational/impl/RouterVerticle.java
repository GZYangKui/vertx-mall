package cn.navigational.impl;

import cn.navigational.annotation.Router;
import cn.navigational.base.BaseVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseTemplate;

public abstract class RouterVerticle extends BaseVerticle {

    public void start() throws Exception {
        vertx.eventBus().<JsonObject>consumer(getAPi(), _msg -> {
            final JsonObject data = _msg.body();
            final String action = data.getString(ACTION);
            final Future<JsonObject> future = dispatch(action,data);
            future.setHandler(_rs -> {
                if (_rs.failed()) {
                    logger.error("业务逻辑处理失败:{}",_rs.cause().getMessage());
                    _msg.reply(error(_rs.cause()).result());
                    return;
                }
                _msg.reply(_rs.result());
            });
        });
    }

    protected abstract Future<JsonObject> dispatch(String action,JsonObject data);

    protected Future<JsonObject> notFound(final String action) {
        final Promise<JsonObject> promise = Promise.promise();
        final JsonObject msg = responseFailed("API NOT FOUND", 404);
        msg.put(REQUEST_API, action);
        promise.complete(msg);
        return promise.future();
    }

    private Future<JsonObject> error(Throwable _t) {
        final Promise<JsonObject> promise = Promise.promise();
        final JsonObject msg = responseFailed("程序出错", 500);
        msg.put(CAUSE, Objects.isNull(_t.getMessage()) ? "NULL" : _t.getMessage());
        promise.complete(msg);
        return promise.future();
    }
    private String getAPi(){
        final Router router = this.getClass().getDeclaredAnnotation(Router.class);
        if (Objects.nonNull(router)){
            return router.api();
        }else {
            return "";
        }
    }
}
