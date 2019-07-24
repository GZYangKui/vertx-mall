package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.service.CouponService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;


public class CouponServiceImpl extends BaseService implements CouponService {
    public CouponServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();


        return promise.future();
    }
}
