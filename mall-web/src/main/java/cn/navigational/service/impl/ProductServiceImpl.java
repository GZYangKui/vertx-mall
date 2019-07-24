package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.service.ProductService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductDao dao;

    public ProductServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.list(getPaging(obj)).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }
}
