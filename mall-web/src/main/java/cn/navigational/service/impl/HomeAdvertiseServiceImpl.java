package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.HomeAdvertiseDao;
import cn.navigational.dao.impl.HomeAdvertiseDaoImpl;
import cn.navigational.service.HomeAdvertiseService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class HomeAdvertiseServiceImpl extends BaseService implements HomeAdvertiseService {
    private final HomeAdvertiseDao dao = new HomeAdvertiseDaoImpl(vertx, config);

    public HomeAdvertiseServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final int type = Integer.parseInt(getQuery(obj, "type"));
        dao.getList(type).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }
}
