package cn.navigational.service;

import cn.navigational.model.Paging;
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

    /**
     *
     * 获取某个分类下的属性/参数
     * @param cId 分类id
     * @param type 属性/参数
     * @param page 分页查询参数
     * @return 异步返回属性/参数数据集合
     */
    Future<List<JsonObject>> list(int cId,int type,Paging page);

    /**
     *
     * 获取指定分类想指定属性/参数的数量
     * @param type 属性或者参数
     * @param cId 分类Id
     * @return 异步返回统计数量
     */
    Future<Long> countAttrWithType(int cId,int type);
}
