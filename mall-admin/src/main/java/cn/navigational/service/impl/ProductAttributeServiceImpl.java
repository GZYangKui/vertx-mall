package cn.navigational.service.impl;

import cn.navigational.dao.ProductAttributeDao;
import cn.navigational.service.ProductAttributeService;
import cn.navigational.service.ProductService;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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
}
