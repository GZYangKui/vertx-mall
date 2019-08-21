package cn.navigational.service.impl;


import cn.navigational.dao.CouponDao;
import cn.navigational.dao.impl.CouponDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.CouponService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;



public class CouponServiceImpl  implements CouponService {

    private CouponDao dao;

    public CouponServiceImpl(Vertx vertx, JsonObject config) {
        dao =  new CouponDaoImpl(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();

        dao.getList(paging).setHandler(_rs -> {
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
