package cn.navigational.service.impl;

import cn.navigational.dao.CartDao;
import cn.navigational.model.Paging;
import cn.navigational.service.CartService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class CartServiceImpl implements CartService {

    private final CartDao dao;

    public CartServiceImpl(Vertx vertx, JsonObject config){
        dao = new CartDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(long userId, Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getList(userId, paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }

    @Override
    public long computer(List<JsonObject> list) {
        long totalAmount = 0;
        for (JsonObject _item : list) {
            final Optional<Integer> optional = list.stream()
                    .filter(_rr -> _rr.getInteger("id") == _item.getInteger("id"))
                    .map(_rr -> _rr.getInteger("count")).findAny();
            if (optional.isPresent()) {
                totalAmount += _item.getLong("price") * optional.get();
            }
        }

        return totalAmount;
    }

    @Override
    public Future<JsonObject> updateNum(long number,long cartId) {
        final Promise<JsonObject> promise = Promise.promise();
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
                    updateNum(number,cartId);
                } else {
                    promise.complete(responseSuccessJson());
                }
            });
        });
        return promise.future();
    }

    @Override
    public Future<List<Integer>> deletes(List<Integer> cartIds) {
        final Promise<List<Integer>> promise = Promise.promise();

        dao.deletes(cartIds).setHandler(_r -> {
            if (_r.failed()) {
                promise.fail(_r.cause());
                return;
            }
            promise.complete(List.of());
        });

        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> getProducts(List<JsonObject> list) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        final List<Integer> ids = list.stream().map(_r -> _r.getInteger("id")).collect(Collectors.toList());
        dao.getItems(ids).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().isEmpty() || _rs.result().size() != list.size() ? List.of() : _rs.result());
        });
        return promise.future();
    }
}
