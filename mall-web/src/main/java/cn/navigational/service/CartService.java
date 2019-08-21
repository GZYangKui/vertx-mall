package cn.navigational.service;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface CartService {
    /**
     * 获取购物车列表
     */
    Future<List<JsonObject>> list(long userId, Paging paging);

    /**
     * 计算选中商品总金额
     */
    long computer(List<JsonObject> list);

    /**
     *
     * 更新购物车商品数量
     *
     * @param number 更新数量
     * @param  cartId 购物城车id
     *
     */
    Future<JsonObject> updateNum(long number,long cartId);

    /**
     *
     * 删除商品
     *
     */
    Future<List<Integer>> deletes(List<Integer> obj);

    /**
     *
     * 获取指定商品集合
     * @param list 商品id列表
     * @return 如果获取到全部商品 返回商品列表 否则返回空集合
     *
     */
    Future<List<JsonObject>> getProducts(List<JsonObject> list);
}
