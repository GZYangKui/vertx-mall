package cn.navigational.service.impl;

import cn.navigational.dao.BrandDao;
import cn.navigational.model.query.BrandQueryParam;
import cn.navigational.service.BrandService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class BrandServiceImpl implements BrandService {
    private BrandDao dao;
    private static final Logger logger = LogManager.getLogger(BrandServiceImpl.class);

    public BrandServiceImpl(Vertx vertx, JsonObject config) {
        dao = new BrandDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(BrandQueryParam param) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.list(param).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("获取品牌列表失败:{}", nullableStr(ar.cause()));
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }

    @Override
    public Future<Long> count(BrandQueryParam param) {
        Promise<Long> promise = Promise.promise();
        dao.count(param).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                logger.error("统计品牌数量过程发生错误:{}", nullableStr(ar.cause()));
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }
}
