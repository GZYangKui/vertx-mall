package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface CartService {
    /**
     * 获取购物车列表
     */
    Future<JsonObject> list(JsonObject obj);

    /**
     * 计算选中商品总金额
     */
    Future<JsonObject> computer(JsonObject obj);

    /**
     *
     * 更新购物车商品数量
     *
     */
    Future<JsonObject> updateNum(JsonObject obj);

    /**
     *
     * 删除商品
     *
     */
    Future<JsonObject> deletes(JsonObject obj);
}
