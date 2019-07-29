package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.CartDao;
import cn.navigational.dao.impl.CartDaoImpl;
import cn.navigational.service.CartService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.config.Constants.BODY;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class CartServiceImpl extends BaseService implements CartService {

    private final CartDao dao = new CartDaoImpl(vertx, config);

    public CartServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final long userId = getUser(obj).getUserId();
        dao.getList(userId, getPaging(obj)).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> computer(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final List<JsonObject> list = obj.getJsonArray(BODY).getList();
        if (list.isEmpty()) {
            promise.complete(responseFailed("请选择商品", 200));
            return promise.future();
        }
        final List<Integer> ids = list.stream().map(_r -> _r.getInteger("id")).collect(Collectors.toList());
        dao.getItems(ids).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> temp = _rs.result();
            if (temp.isEmpty()) {
                promise.complete(responseFailed("找不到对应的商品,请重新添加购物车", 200));
                return;
            }
            long totalAmount = 0;
            for (JsonObject _item : temp) {
                final Optional<Integer> optional = list.stream()
                        .filter(_rr -> _rr.getInteger("id") == _item.getInteger("id"))
                        .map(_rr -> _rr.getInteger("count")).findAny();
                if (optional.isPresent()) {
                    totalAmount += _item.getLong("price") * optional.get();
                }
            }
            promise.complete(responseSuccessJson(new JsonObject().put("totalAMount", totalAmount)));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> updateNum(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final JsonObject info = obj.getJsonObject(BODY);
        final long cartId = info.getLong("id");
        dao.item(cartId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> result = _rs.result();
            if (result.isEmpty()) {
                promise.complete(responseFailed("找不到商品信息,请重新添加到购物车.", 200));
                return;
            }
            //获取当前cas版本号
            final long version = result.get().getLong("version");
            //获取当前商品数量
            final long quantity = result.get().getLong("quantity");
            //获取用户上传上来的数量
            final long number = info.getLong("number");
            if (number < 0 && quantity - number <= 0) {
                promise.complete(responseFailed("商品数量不能小于1", 200));
                return;
            }
            final long newQuantity = quantity + number;
            dao.updateCartNum(version, newQuantity, cartId).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                if (_r.result() <= 0) {
                    updateNum(obj);
                } else {
                    promise.complete(responseSuccessJson());
                }
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> deletes(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();

        final List<Integer> cartIds = obj.getJsonArray(BODY).getList();
        dao.deletes(cartIds).setHandler(_r -> {
            if (_r.failed()) {
                promise.fail(_r.cause());
                return;
            }
            promise.complete(responseSuccessJson());
        });
        return promise.future();
    }
}
