package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface HomeAdvertiseService {
    /**
     *
     * 获取商城首页广告位
     *
     * @param obj
     * @return
     */
    Future<JsonObject> list(JsonObject obj);
}
