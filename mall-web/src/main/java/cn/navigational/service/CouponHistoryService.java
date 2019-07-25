package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public interface CouponHistoryService {
    /**
     * 获取优惠券领取记录
     *
     * @param obj
     * @return
     */
    Future<JsonObject> list(JsonObject obj);

    /**
     * 删除领取记录
     *
     * @param obj
     */
    Future<JsonObject> deleteHistory(JsonObject obj);
}
