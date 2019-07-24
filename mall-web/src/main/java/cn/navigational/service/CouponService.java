package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public interface CouponService {
    /**
     *
     * 获取优惠券列表
     *
     *
     */
    Future<JsonObject> list(JsonObject obj);
}
