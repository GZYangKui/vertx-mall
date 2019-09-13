package cn.navigational.service;

import cn.navigational.model.query.ProductQueryParamList;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductService {
    /**
     * 获取商品列表
     *
     * @param paramList 指定查询条件
     * @return 异步返回商品列表
     */
    Future<List<JsonObject>> list(ProductQueryParamList paramList);

    /**
     *
     * 统计商品数量
     *
     * @param paramList 查询参数列表
     * @return 异步返回数据数量
     *
     */
    Future<Long> getProductNum(ProductQueryParamList paramList);
}
