package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeService {

    /**
     *
     * 获取商品属性分类列表
     *
     * @param pageNum 页码数目
     * @param  pageSize 页面数据数目
     * @return 异步返回分类集合
     *
     */
    Future<List<JsonObject>> listCategory(long pageNum,long pageSize);

    /**
     *
     * 统计属性分类数量
     *
     * @return 异步返回分类数量
     */
    Future<Long> countAttrCate();

    /**
     *
     * 获取某个分类
     *
     * @param cateName 分类名称
     *
     */
    Future<Optional<JsonObject>> categoryDetail(String cateName);

    /**
     *
     * 创建分类
     * @param cateName 分类名称
     * @return {@link Void}
     */
    Future<Void> createCategory(String cateName);
}
