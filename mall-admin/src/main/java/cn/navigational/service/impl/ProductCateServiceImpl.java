package cn.navigational.service.impl;

import cn.navigational.dao.ProductCateDao;
import cn.navigational.model.query.ProductCateQueryParam;
import cn.navigational.service.ProductCateService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;


public class ProductCateServiceImpl implements ProductCateService {
    private ProductCateDao dao;
    private static final Logger logger = LogManager.getLogger(ProductCateServiceImpl.class);

    public ProductCateServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductCateDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(ProductCateQueryParam param) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.list(param).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("获取商品分类失败:{}", nullableStr(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Long> getCateNum(ProductCateQueryParam param) {
        Promise<Long> promise = Promise.promise();
        dao.count(param).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("统计分类数量过程发生错误:{}", nullableStr(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> listWithChildren(ProductCateQueryParam param) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.list(param).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("获取分类及其子分类失败:{}", nullableStr(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }

            if (ar.result().isEmpty()) {
                promise.complete(Collections.emptyList());
                return;
            }
            //TODO 时间复杂度太高,分类多的时候性能急剧下降,有待改进
            List<JsonObject> list = ar.result();
            List<JsonObject> destory = new ArrayList<>();
            for (JsonObject root : list) {
                long id = root.getLong("id");
                for (JsonObject item : list) {
                    if (item.getLong("parentId") == id) {
                        JsonArray children = root.getJsonArray("children");
                        if (children == null) {
                            children = new JsonArray();
                            root.put("children", children);
                        }
                        children.add(item);
                        destory.add(item);
                    }
                }
            }
            destory.forEach(list::remove);
            promise.complete(list);
        });
        return promise.future();
    }
}
