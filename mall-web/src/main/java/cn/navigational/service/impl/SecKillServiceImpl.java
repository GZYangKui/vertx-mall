package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.SecKillDao;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.dao.impl.SecKillDaoImpl;
import cn.navigational.service.SecKillService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class SecKillServiceImpl extends BaseService implements SecKillService {
    private SecKillDao dao = new SecKillDaoImpl(vertx, config);

    private ProductDao productDao = new ProductDaoImpl(vertx, config);

    public SecKillServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> home(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.getTimeSlot().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(responseSuccessJson());
                return;
            }
            final LocalTime current = LocalTime.now();

            //首先查找是否有当前时间段存在秒杀活动
            final Optional<JsonObject> optional = list.stream().filter(_r -> {
                final LocalTime startTime = LocalTime.parse(_r.getString("start_time"));
                final LocalTime endTime = LocalTime.parse(_r.getString("end_time"));
                return current.toNanoOfDay() >= startTime.toNanoOfDay() && current.toNanoOfDay() <= endTime.toNanoOfDay();
            }).findAny();
            final JsonObject timeSlot;
            //如果当前时间段不存在查早最近时间段
            if (optional.isEmpty()) {
                final Optional<JsonObject> o = list.stream().filter(_r -> {
                    final LocalTime startTime = LocalTime.parse(_r.getString("start_time"));
                    return current.toNanoOfDay() >= startTime.toNanoOfDay();
                }).findFirst();
                //如果为空,说明今天没有秒杀活动
                if (o.isEmpty()) {
                    promise.complete(responseSuccessJson());
                    return;
                }
                timeSlot = o.get();
            } else {
                timeSlot = optional.get();
            }


            dao.getTimeSlotForProduct(timeSlot.getLong("id")).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                final List<JsonObject> temp = _r.result();
                if (temp.isEmpty()) {
                    promise.complete(responseSuccessJson());
                    return;
                }
                final List<Integer> productIds = temp.stream().map(_rr -> _rr.getInteger("product_id")).collect(Collectors.toList());
                productDao.list(productIds).setHandler(_rrr -> {
                    if (_r.failed()) {
                        promise.fail(_rrr.cause());
                        return;
                    }
                    final List<JsonObject> destroy = new ArrayList<>();
                    temp.forEach(_item -> {
                        long productId = _item.getLong("product_id");
                        final Optional<JsonObject> optional1 = _rrr.result().stream().filter(_a -> _a.getLong("id") == productId).findAny();
                        if (optional1.isEmpty()) {
                            destroy.add(_item);
                        } else {
                            final JsonObject product = optional1.get();
                            product.remove("price");
                            _item.put("product", product);
                        }
                    });
                    //去除无效商品
                    destroy.forEach(temp::remove);
                    timeSlot.put("products", temp);
                    promise.complete(responseSuccessJson(timeSlot));
                });
            });
        });
        return promise.future();
    }
}
