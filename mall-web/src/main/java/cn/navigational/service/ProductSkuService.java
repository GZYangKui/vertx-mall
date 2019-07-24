package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface ProductSkuService {
    /**
     *
     * 根据商品id 获取商品库存信息
     *
     */
    Future<JsonObject> sku(JsonObject obj);
}
