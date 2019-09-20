package cn.navigational.service.impl;

import cn.navigational.dao.ProductAttributeDao;
import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttribute;
import cn.navigational.service.ProductAttributeService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

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
        dao.findCategory(cateName).setHandler(ar -> {
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
    public Future<Void> changeCateChildrenNum(int cateId, int type, int status) {
        return null;
    }
}
