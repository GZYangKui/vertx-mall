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

import static cn.navigational.config.Constants.BODY;
import static cn.navigational.utils.ResponseUtils.responseFailed;
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
    public Future<JsonObject> defaultAddress(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JwtUser user = getUser(obj);
        dao.getDefaultAddress(user.getUserId()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isPresent()) {
                promise.complete(responseSuccessJson(new JsonObject()));
            } else {
                promise.complete(responseSuccessJson(optional.get()));
            }
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> detail(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final int addressId = Integer.parseInt(getQuery(obj, "address_id"));
        dao.getDetail(addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isEmpty()) {
                promise.complete(responseFailed("未找到对应的地址信息", 200));
                return;
            }
            promise.complete(responseSuccessJson(optional.get()));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> update(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JsonObject info = obj.getJsonObject(BODY);
        final int addressId = info.getInteger("id");
        dao.getDetail(addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isEmpty()) {
                promise.complete(responseFailed("地址信息不存在", 200));
                return;
            }
            final boolean isUpdateDefault = info.getInteger("isDefault") != optional.get().getInteger("default_status") && info.getInteger("isDefault") == 1;

            //更新地址信息
            dao.updateAddress(info).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                promise.complete(responseSuccessJson());
                if (isUpdateDefault) {
                    dao.updateDefaultAddress(info.getInteger("id"));
                }
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> create(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JsonObject info = obj.getJsonObject(BODY);
        final long userId = getUser(obj).getUserId();

        dao.create(info, userId).setHandler(_r -> {
            if (_r.failed()) {
                promise.fail(_r.cause());
                return;
            }

            final int id = _r.result();

            if (info.getInteger("isDefault") == 1) {
                dao.updateDefaultAddress(id);
            }
            promise.complete(responseSuccessJson());
        });

        return promise.future();
    }
}
