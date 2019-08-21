package cn.navigational.service;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;


public interface ProductService {
    /**
     *
     * 获取商品列表
     * @param paging 分页查询参数
     *
     */
    Future<List<JsonObject>> list(Paging paging);

    /**
     *
     *
     * 获取今日推荐商品信息
     * @param  paging 分页查询参数
     *
     */
    Future<List<JsonObject>> recommendProduct(Paging paging);
}
