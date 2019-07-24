package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.AddressDao;
import cn.navigational.dao.impl.AddressDaoImpl;
import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import cn.navigational.service.AddressService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Optional;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class AddressServiceImpl extends BaseService implements AddressService {
    private final AddressDao dao;

    public AddressServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new AddressDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JwtUser user = getUser(obj);
        final Paging page = getPaging(obj);
        dao.getAddressList(user.getUserId(), page).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> getDefaultAddress(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JwtUser user = getUser(obj);
        dao.getDefaultAddress(user.getUserId()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isEmpty()) {
                promise.complete(responseSuccessJson(new JsonObject()));
            } else {
                promise.complete(responseSuccessJson(optional.get()));
            }
        });
        return promise.future();
    }
}
