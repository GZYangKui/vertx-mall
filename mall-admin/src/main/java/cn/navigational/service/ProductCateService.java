package cn.navigational.service;


import cn.navigational.model.query.ProductCateQueryParam;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductCateService {
    /**
     * 获取商品分类列表
     *
     * @param param 查询参数
     * @return 异步返回JSON数据集合
     */
    Future<List<JsonObject>> list(ProductCateQueryParam param);

    /**
     * 统计商品分类数量
     *
     * @param param 查询参数
     */
    Future<Long> getCateNum(ProductCateQueryParam param);

    /**
     * 列出分类及其子分类
     *
     * @param param 查询参数
     */
    Future<List<JsonObject>> listWithChildren(ProductCateQueryParam param);
}
