package cn.navigational.service.impl;

import cn.navigational.dao.HomeAdvertiseDao;
import cn.navigational.service.HomeAdvertiseService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;


public class HomeAdvertiseServiceImpl implements HomeAdvertiseService {
    private final HomeAdvertiseDao dao;

    public HomeAdvertiseServiceImpl(Vertx vertx, JsonObject config) {
        dao = new HomeAdvertiseDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(int type) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getList(type).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }
}
