package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductSkuService {
    /**
     *
     * 根据商品id 获取商品库存信息
     * @param productId 商品id
     */
    Future<List<JsonObject>> sku(int productId);
}
