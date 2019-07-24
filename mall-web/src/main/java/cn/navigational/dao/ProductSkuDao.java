package cn.navigational.dao;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductSkuDao {
    /**
     *
     * 获取库存信息
     * @param productId 商品id
     * @return
     */
    Future<List<JsonObject>> getSku(int productId);
}
