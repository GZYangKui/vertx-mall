package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.ProductService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductDao dao;

    public ProductServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductDaoImpl(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.list(paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> recommendProduct(Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getRecommend(paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }
}
