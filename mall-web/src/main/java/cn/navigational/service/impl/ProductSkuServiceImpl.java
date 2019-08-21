package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductSkuDao;
import cn.navigational.dao.impl.ProductSkuDaoImpl;
import cn.navigational.service.ProductSkuService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class ProductSkuServiceImpl extends BaseService implements ProductSkuService {

    private final ProductSkuDao dao;

    public ProductSkuServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductSkuDaoImpl(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> sku(int productId) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getSku(productId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            promise.complete(list);
        });
        return promise.future();
    }
}
