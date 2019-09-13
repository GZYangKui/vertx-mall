package cn.navigational.service.impl;

import cn.navigational.dao.ProductDao;
import cn.navigational.model.query.ProductQueryParamList;
import cn.navigational.service.ProductService;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class ProductServiceImpl implements ProductService {
    private ProductDao dao;
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(ProductQueryParamList paramList) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.getProductList(paramList).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取商品列表失败:{}", ExceptionUtils.nullableStr(ar.cause()));
                promise.fail(ar.cause());
                ar.cause().printStackTrace();
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }
}
