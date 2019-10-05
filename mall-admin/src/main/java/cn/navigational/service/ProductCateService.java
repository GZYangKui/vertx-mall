package cn.navigational.service;


import cn.navigational.model.query.ProductCateQueryParam;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductCateService {
    /**
     * 获取商品分类列表
     *
     * @param parentId  父分类id
     * @param pageIndex 起始页
     * @param pageSize  页面尺寸
     * @return 异步返回JSON数据集合
     */
    Future<List<JsonObject>> list(int parentId, int pageIndex, int pageSize);

    /**
     * 统计商品分类数量
     *
     * @param parentId 父级
     */
    Future<Long> getCateNum(int parentId);

    //列出商品分类及其子分类
    Future<List<JsonObject>> listWithChildren();

    /**
     * 更新显示状态
     *
     * @param ids        f分类id集合
     * @param showStatus 显示状态
     */
    Future<Void> updateShowStatus(List<Integer> ids, int showStatus);

    /**
     * 更新分类导航状态
     *
     * @param ids
     * @param navStatus 导航状态
     * @return
     */
    Future<Void> updateNavStatus(List<Integer> ids, int navStatus);
}
