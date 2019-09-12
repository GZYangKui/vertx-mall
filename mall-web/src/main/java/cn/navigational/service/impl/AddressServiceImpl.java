package cn.navigational.service.impl;

import cn.navigational.dao.AddressDao;
import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import cn.navigational.service.AddressService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;


public class AddressServiceImpl implements AddressService {
    private final AddressDao dao;

    public AddressServiceImpl(Vertx vertx, JsonObject config) {
        dao = new AddressDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging, JwtUser user) {
        final Promise<List<JsonObject>> promise = Promise.promise();

        dao.getAddressList(user.getUserId(), paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });

        return promise.future();
    }

    @Override
    public Future<JsonObject> defaultAddress(JwtUser user) {
        final Promise<JsonObject> promise = Promise.promise();

        dao.getDefaultAddress(user.getUserId()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().orElse(new JsonObject()));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> detail(int addressId) {
        final Promise<JsonObject> promise = Promise.promise();

        dao.getDetail(addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().orElse(new JsonObject()));
        });
        return promise.future();
    }

    @Override
    public Future<Boolean> update(JsonObject info, long userId, int addressId) {
        final Promise<Boolean> promise = Promise.promise();
        dao.getDetail(addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();

            if (optional.isEmpty()) {
                promise.complete(false);
                return;
            }

            final boolean isUpdateDefault = info.getInteger("isDefault") != optional.get().getInteger("default_status") && info.getInteger("isDefault") == 1;

            //更新地址信息
            dao.updateAddress(info).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                promise.complete(true);
                if (isUpdateDefault) {
                    dao.updateDefaultAddress(userId, info.getInteger("id"));
                }
            });
        });
        return promise.future();
    }

    @Override
    public Future<Boolean> create(JsonObject info, long userId) {
        final Promise<Boolean> promise = Promise.promise();

        dao.create(info, userId).setHandler(_r -> {
            if (_r.failed()) {
                promise.fail(_r.cause());
                return;
            }
            final int id = _r.result();

            if (info.getInteger("isDefault") == 1) {
                dao.updateDefaultAddress(userId, id);
            }
            promise.complete(true);
        });

        return promise.future();
    }

    @Override
    public Future<Boolean> updateDefault(int addressId, long userId) {
        final Promise<Boolean> promise = Promise.promise();
        dao.getDetail(addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isEmpty()) {
                promise.complete(false);
                return;
            }

            final JsonObject address = optional.get();
            if (address.getInteger("default_status") == 1) {
                promise.complete(true);
                return;
            }
            dao.setDefault(userId, addressId).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                promise.complete(true);
                dao.updateDefaultAddress(userId, addressId);
            });
        });
        return promise.future();
    }

    @Override
    public Future<Boolean> delete(int addressId, long userId) {
        final Promise<Boolean> promise = Promise.promise();
        dao.delete(userId, addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result() > 0);
        });
        return promise.future();
    }
}
