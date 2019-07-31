package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public interface ProductService {
    /**
     *
     * 获取商品列表
     *
     *
     */
    Future<JsonObject> list(JsonObject obj);

    /**
     *
     *
     * 获取今日推荐商品信息
     *
     *
     */
    Future<JsonObject> recommendProduct(JsonObject object);
}
