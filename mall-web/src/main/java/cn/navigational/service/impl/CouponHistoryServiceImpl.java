package cn.navigational.service.impl;


import cn.navigational.dao.CouponHistoryDao;
import cn.navigational.dao.impl.CouponHistoryDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.CouponHistoryService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CouponHistoryServiceImpl implements CouponHistoryService {
    private final CouponHistoryDao dao;

    public CouponHistoryServiceImpl(Vertx vertx, JsonObject config) {
        dao = new CouponHistoryDaoImpl(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(long userId, Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getList(userId, paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            if (_rs.result().isEmpty()) {
                promise.complete(List.of());
                return;
            }
            final List<JsonObject> list = _rs.result();
            final List<Integer> ids = list.stream().map(_r -> _r.getInteger("coupon_id")).collect(Collectors.toList());
            dao.getCoupon(ids).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                //优惠券已经不存在,存储然后异步删除

                final JsonArray destroy = new JsonArray();
                list.forEach(_i -> {
                    final Optional<JsonObject> optional = _rr.result().stream().filter(_in -> _in.getInteger("id") == _i.getInteger("coupon_id")).findAny();
                    if (optional.isEmpty()) {
                        destroy.add(_i.getInteger("id"));
                        return;
                    }
                    _i.put("coupon", optional.get());
                });
                if (!destroy.isEmpty()) {
                    deleteHistory(destroy);
                }
                promise.complete(list);
            });
        });
        return promise.future();
    }

    @Override
    public Future<Integer> deleteHistory(JsonArray ids) {
        final Promise<Integer> promise = Promise.promise();
        dao.deleteHistory(ids).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }
}
