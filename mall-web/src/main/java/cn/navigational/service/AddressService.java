package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface AddressService {
    /**
     *
     * 获取收货地址列表
     *
     * @param obj
     * @return
     */
    Future<JsonObject> list(JsonObject obj);

    /**
     *
     *
     * 获取默认收货地址
     *
     */
    Future<JsonObject> getDefaultAddress(JsonObject obj);
}
