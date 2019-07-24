package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductCateDao;
import cn.navigational.dao.impl.ProductCateDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.ProductCateService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Comparator;
import java.util.List;


import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class ProductCateServiceImpl extends BaseService implements ProductCateService {
    private final ProductCateDao dao;

    public ProductCateServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductCateDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final Paging page = getPaging(obj);
        dao.getCateList(page).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            //倒叙排列
            list.sort(Comparator.comparing(_rr->((JsonObject)_rr).getInteger("sort")).reversed());
            promise.complete(responseSuccessJson(list));
        });

        return promise.future();

    }
}
