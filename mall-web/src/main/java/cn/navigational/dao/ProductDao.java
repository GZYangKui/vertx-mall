package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;


public interface ProductDao {
    /**
     * 获取商品列表
     *
     * @param paging 分页查询参数
     * @return 异步返回json商品集合
     */
    Future<List<JsonObject>> list(Paging paging);

    /**
     * 获取制定id商品信息
     *
     * @param productIds 商品id集合
     * @return 异步返回json商品集合
     *
     */
    Future<List<JsonObject>> list(List<Integer> productIds);

    /**
     * 获取今日推荐商品
     *
     * @param paging 分页查询参数
     * @return 异步返回推荐商品列表
     *
     */
    Future<List<JsonObject>> getTodayRecommend(Paging paging);
}
