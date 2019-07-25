package cn.navigational.service.impl;

import cn.navigational.base.BaseService;

import cn.navigational.dao.impl.CouponHistoryDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.CouponHistoryService;
import cn.navigational.dao.CouponHistoryDao;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.config.Constants.BODY;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class CouponHistoryServiceImpl extends BaseService implements CouponHistoryService {
    private final CouponHistoryDao dao = new CouponHistoryDaoImpl(vertx, config);

    public CouponHistoryServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final long userId = getUser(obj).getUserId();
        final Paging paging = getPaging(obj);
        dao.getList(userId, paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
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
                final List<JsonObject> destroy = new ArrayList<>();
                list.forEach(_i -> {
                    final Optional<JsonObject> optional = _rr.result().stream().filter(_in -> _in.getInteger("id") == _i.getInteger("coupon_id")).findAny();
                    if (optional.isEmpty()) {
                        destroy.add(_i);
                        return;
                    }
                    _i.put("coupon", optional.get());
                });
                if (!destroy.isEmpty()) {
                    final List<Integer> temp = destroy.stream().map(_r -> _r.getInteger("id")).collect(Collectors.toList());
                    final JsonObject msg = new JsonObject();
                    msg.put(BODY, new JsonObject().put("ids", temp));
                    deleteHistory(msg);
                }
                promise.complete(responseSuccessJson(list));
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> deleteHistory(JsonObject obj) {
        final JsonArray deleteList = obj.getJsonObject(BODY).getJsonArray("ids");

        return null;
    }
}
