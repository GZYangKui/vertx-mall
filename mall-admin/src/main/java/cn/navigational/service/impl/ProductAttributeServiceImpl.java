package cn.navigational.service.impl;

import cn.navigational.dao.ProductAttributeDao;
import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttributCategory;
import cn.navigational.model.ProductAttribute;
import cn.navigational.service.ProductAttributeService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class ProductAttributeServiceImpl implements ProductAttributeService {
    private ProductAttributeDao dao;
    private static final Logger logger = LogManager.getLogger(ProductAttributeService.class);

    public ProductAttributeServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductAttributeDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> listCategory(long pageNum, long pageSize) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.getAttrCateList(pageNum, pageSize).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取分类列表失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Long> countAttrCate() {
        Promise<Long> promise = Promise.promise();
        dao.countAttrCate().setHandler(ar -> {
            if (ar.failed()) {
                logger.error("统计分类数量失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Optional<JsonObject>> categoryDetail(String cateName) {
        Promise<Optional<JsonObject>> promise = Promise.promise();
        dao.findCategoryByName(cateName).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取分类详情失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Void> createCategory(String cateName) {
        Promise<Void> promise = Promise.promise();
        dao.createCatefory(cateName).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("新增分类失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete();
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> list(int cId, int type, Paging page) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.listAttr(cId, type, page).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取属性/分类列表失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Long> countAttrWithType(int cId, int type) {
        Promise<Long> promise = Promise.promise();
        dao.countAttr(cId, type).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("统计属性/分类过程发生错误:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Void> createAttribute(ProductAttribute attribute) {
        Promise<Void> promise = Promise.promise();
        dao.createAttribute(attribute).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("创建参数/规格失败:{}", nullableStr(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }
            int updated = ar.result();
            if (updated > 0) {
                promise.complete();
            } else {
                promise.fail("插入失败");
            }
        });
        return promise.future();
    }

    @Override
    public Future<Optional<JsonObject>> getProductAttribute(ProductAttribute attribute) {
        return dao.findAttribute(attribute);
    }

    @Override
    public Future<Void> changeCateChildrenNum(int cateId, int type, int val, final Promise<Void> promise, int flag) {
        if (flag >= 4) {
            promise.fail("CAS尝试多次旋转失败!");
            logger.info("CAS 尝试多次自螺旋修改商品分类/规格分类数量失败");
            return Future.failedFuture("");
        }
        categoryDetail(cateId).compose(r -> {
            if (r.isEmpty()) {
                return Future.failedFuture("分类不存在");
            }
            ProductAttributCategory cate = r.get().mapTo(ProductAttributCategory.class);
            return dao.updateCateAttrNum(cate, type, val);
        }).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("更新商品属性分类规格/参数数量失败:{}", nullableStr(ar.cause()));
                return;
            }
            int updated = ar.result();
            if (updated > 0) {
                promise.complete();
            } else {
                changeCateChildrenNum(cateId, type, val, promise, flag + 1);
            }
        });
        return promise.future();
    }

    @Override
    public Future<Optional<JsonObject>> categoryDetail(int cateId) {
        Promise<Optional<JsonObject>> promise = Promise.promise();
        dao.findCategoryById(cateId).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取分类详情失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Integer> deleteCategory(int cateId) {
        return dao.deleteCategory(cateId);
    }

    @Override
    public Future<Integer> deleteCateAttr(int cateId) {
        return dao.deleteCateAttr(cateId);
    }

    @Override
    public Future<Integer> updateCate(int cateId, String name) {
        return dao.updateCate(cateId, name);
    }

    @Override
    public Future<List<ProductAttribute>> listAttr(List<Integer> ids) {
        Promise<List<ProductAttribute>> promise = Promise.promise();
        dao.findAttrById(ids).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("批量获取属性失败:{}", nullableStr(ar.cause()));
                return;
            }
            List<ProductAttribute> attrs = null;
            try {
                attrs = ar.result().stream().map(r -> r.mapTo(ProductAttribute.class)).collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            promise.complete(attrs);
        });
        return promise.future();
    }

    @Override
    public Future<Void> deleteAttr(List<Integer> ids) {
        Promise<Void> promise = Promise.promise();
        dao.deleteAttr(ids).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("批量删除属性失败:{}", nullableStr(ar.cause()));
                return;
            }
            logger.info("批量删除{}条属性/规格", ar.result());
            promise.complete();
        });
        return promise.future();
    }

    @Override
    public void batchUpdateAttrCate(List<ProductAttribute> attrs, int val) {
        attrs.forEach(attr -> {
            changeCateChildrenNum(attr.getProductAttributeCategoryId(), attr.getType(), val, Promise.promise(), 1);
        });
    }
    @Override
    public Future<Optional<JsonObject>> getAttr(int attrId){
        return dao.findAttrById(attrId);
    }

    @Override
    public Future<Integer> updateAttr(ProductAttribute attr) {
        return dao.updateAttr(attr);
    }
}
