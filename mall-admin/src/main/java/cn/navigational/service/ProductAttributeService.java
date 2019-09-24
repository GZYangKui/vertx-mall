package cn.navigational.service;

import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttributCategory;
import cn.navigational.model.ProductAttribute;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeService {

    /**
     * 获取商品属性分类列表
     *
     * @param pageNum  页码数目
     * @param pageSize 页面数据数目
     * @return 异步返回分类集合
     */
    Future<List<JsonObject>> listCategory(long pageNum, long pageSize);

    /**
     * 统计属性分类数量
     *
     * @return 异步返回分类数量
     */
    Future<Long> countAttrCate();

    /**
     * 获取某个分类
     *
     * @param cateName 分类名称
     */
    Future<Optional<JsonObject>> categoryDetail(String cateName);

    /**
     * 获取某个分类根据分类id
     *
     * @param cateId 分类id
     */
    Future<Optional<JsonObject>> categoryDetail(int cateId);

    /**
     * 创建分类
     *
     * @param cateName 分类名称
     * @return {@link Void}
     */
    Future<Void> createCategory(String cateName);

    /**
     * 获取某个分类下的属性/参数
     *
     * @param cId  分类id
     * @param type 属性/参数
     * @param page 分页查询参数
     * @return 异步返回属性/参数数据集合
     */
    Future<List<JsonObject>> list(int cId, int type, Paging page);

    /**
     * 获取指定分类想指定属性/参数的数量
     *
     * @param type 属性或者参数
     * @param cId  分类Id
     * @return 异步返回统计数量
     */
    Future<Long> countAttrWithType(int cId, int type);

    /**
     * 创建属性/规格
     *
     * @param attribute 商品属性/规格
     */
    Future<Void> createAttribute(ProductAttribute attribute);

    /**
     * 获取商品属性/规格
     *
     * @param attribute 商品属性/规格
     * @return {@link Optional<JsonObject>}
     */
    Future<Optional<JsonObject>> getProductAttribute(ProductAttribute attribute);

    /**
     * 增加分类下参数/规格数目
     *
     * @param cateId  分类id
     * @param type    规格/参数
     * @param val     增加/减少的具体值 大于零添加 小于零减少
     * @param promise 这个参数只是配合CAS更新添加的
     * @param flag 记录执行次数
     */
    Future<Void> changeCateChildrenNum(int cateId, int type, int val, Promise<Void> promise,int flag);

    /**
     * 删除分类
     *
     * @param cateId 分类id
     */
    Future<Integer> deleteCategory(int cateId);

    /**
     * 删除分类下的规格/参数
     *
     * @param cateId 参数id
     */
    Future<Integer> deleteCateAttr(int cateId);

    /**
     * 更新分类名称
     *
     * @param cateId 分类id
     * @param name   分类名称
     */
    Future<Integer> updateCate(int cateId, String name);

    /**
     *
     * 获取规格/参数
     * @param ids 属性/规格id列表
     * @return 异步返回属性列表
     */
    Future<List<ProductAttribute>> listAttr(List<Integer> ids);
    /**
     *
     * 批量删除属性
     * @param ids 被删除属性id集合
     */
    Future<Void> deleteAttr(List<Integer> ids);

    /**
     *
     * 批量更新商品参数或者规格分类
     *
     * @param attrs 分类集合
     * @param val 值
     */
    void batchUpdateAttrCate(List<ProductAttribute> attrs,int val);

    /**
     *
     *
     * 获取属性详情
     * @param attrId 属性/规格参数
     *
     */
    Future<Optional<JsonObject>> getAttr(int attrId);

}
