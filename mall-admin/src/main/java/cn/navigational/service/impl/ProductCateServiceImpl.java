package cn.navigational.service.impl;

import cn.navigational.dao.ProductCateDao;
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
    public Future<List<JsonObject>> list(int parentId, int pageIndex, int pageSize) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.list(parentId, pageIndex, pageSize).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("获取分类失败:{}", nullableStr(ar.cause()));
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Long> getCateNum(int parentId) {
        Promise<Long> promise = Promise.promise();
        dao.count(parentId).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("统计分类数量过程发生错误:{}", nullableStr(ar.cause()));
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> listWithChildren() {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.list().setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("获取分类及其子分类失败:{}", nullableStr(ar.cause()));
                return;
            }

            if (ar.result().isEmpty()) {
                promise.complete(Collections.emptyList());
                return;
            }
            //TODO 时间复杂度太高,分类多的时候性能急剧下降,有待改进
            List<JsonObject> list = ar.result();
            List<JsonObject> destroy = new ArrayList<>();
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
                        destroy.add(item);
                    }
                }
            }
            destroy.forEach(list::remove);
            promise.complete(list);
        });
        return promise.future();
    }

    @Override
    public Future<Void> updateShowStatus(List<Integer> ids, int showStatus) {
        Promise<Void> promise = Promise.promise();

        dao.updateShowStatus(ids, showStatus).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("更新显示状态失败:{}", nullableStr(ar.cause()));
                return;
            }
            logger.info("成功更新{}条分类显示状态", ar.result());
            promise.complete();
        });
        return promise.future();
    }

    @Override
    public Future<Void> updateNavStatus(List<Integer> ids, int navStatus) {
        Promise<Void> promise = Promise.promise();
        dao.updateNavStatus(ids, navStatus).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("更新导航状态失败:{}", nullableStr(ar.cause()));
                return;
            }
            logger.info("成功更新{}条分类导航状态", ar.result());
            promise.complete();
        });
        return promise.future();
    }
}
