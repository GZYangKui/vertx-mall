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
    Future<JsonObject> defaultAddress(JsonObject obj);


    /**
     *
     * 获取地址详情
     *
     */
    Future<JsonObject> detail(JsonObject obj);

    /**
     *
     * 更新地址信息
     *
     *
     */
    Future<JsonObject> update(JsonObject obj);

    /**
     *
     *
     * 新建收货地址
     *
     */
    Future<JsonObject> create(JsonObject obj);

}
