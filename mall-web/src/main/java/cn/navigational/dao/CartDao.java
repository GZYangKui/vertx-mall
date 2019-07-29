package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface CartDao {
    /**
     * 从数据库查找购物车信息
     *
     * @param userId 用户id
     * @param paging 分页查询参数
     * @return 返回异步json集合
     */
    Future<List<JsonObject>> getList(long userId, Paging paging);

    /**
     * 从数据库查找给定的item
     *
     * @param ids items id列表
     */
    Future<List<JsonObject>> getItems(List<Integer> ids);

    /**
     * 获取某个购物车item
     *
     * @param cartId item id
     */
    Future<Optional<JsonObject>> item(long cartId);

    /**
     * 更新购物车商品数量
     *
     * @param version  CAS版本号
     * @param quantity 新商品数量
     * @param cartId   购物车id
     */
    Future<Integer> updateCartNum(long version, long quantity, long cartId);

    /**
     * 删除购物车内的商品
     *
     * @param ids 即将被删除购物车商品id集合
     */
    Future<Integer> deletes(List<Integer> ids);
}
