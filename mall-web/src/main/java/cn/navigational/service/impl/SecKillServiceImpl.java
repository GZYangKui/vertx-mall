package cn.navigational.service.impl;

import cn.navigational.dao.ProductDao;
import cn.navigational.dao.SecKillDao;
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



public class SecKillServiceImpl implements SecKillService {
    private SecKillDao dao;

    private ProductDao productDao;

    public SecKillServiceImpl(Vertx vertx, JsonObject config) {
        dao = new SecKillDao(vertx, config);
        productDao = new ProductDao(vertx, config);
    }

    @Override
    public Future<JsonObject> home() {
        final Promise<JsonObject> promise = Promise.promise();
        dao.getTimeSlot().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(new JsonObject());
                return;
            }

            final LocalTime current = LocalTime.now();

            //秒杀时间段信息
            final JsonObject timeSlot;

            //首先查找是否有当前时间段存在秒杀活动
            final Optional<JsonObject> optional = list.stream().filter(_r -> {
                final LocalTime startTime = LocalTime.parse(_r.getString("start_time"));
                final LocalTime endTime = LocalTime.parse(_r.getString("end_time"));
                return current.toNanoOfDay() >= startTime.toNanoOfDay() && current.toNanoOfDay() <= endTime.toNanoOfDay();
            }).findAny();
            //如果当前时间段不存在查早最近时间段
            if (optional.isEmpty()) {
                final Optional<JsonObject> o = list.stream().filter(_r -> {
                    final LocalTime startTime = LocalTime.parse(_r.getString("start_time"));
                    return current.toNanoOfDay() >= startTime.toNanoOfDay();
                }).findFirst();
                //如果为空,说明今天没有秒杀活动
                if (o.isEmpty()) {
                    promise.complete(new JsonObject());
                    return;
                }
                timeSlot = o.get();
            } else {
                timeSlot = optional.get();
            }
            final long timeSlotId = timeSlot.getLong("id");
            getProducts(timeSlotId).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                timeSlot.put("products", _r.result());
                promise.complete(timeSlot);
            });
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> timeSlots() {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getTimeSlot().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            list.forEach(_item -> {
                final long now = LocalTime.now().toNanoOfDay();
                final long startTime = LocalTime.parse(_item.getString("start_time")).toNanoOfDay();
                final long endTime = LocalTime.parse(_item.getString("end_time")).toNanoOfDay();
                //设置当前状态为抢购中
                if (startTime <= now && now <= endTime) {
                    _item.put("status", 1);
                } else if (now > endTime) {
                    //设置状态已开抢
                    _item.put("status", 2);
                } else {
                    //设置状态为即将开抢
                    _item.put("status", 3);
                }
            });
            promise.complete(list);
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> timeSlotWithProduct(long id) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        getProducts(id).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }

    /**
     * 获取产品列表
     *
     * @param slotId @分段时间列表
     * @return 发布异步商品集合
     */
    private Future<List<JsonObject>> getProducts(long slotId) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        //得到时间段对应的商品信息
        dao.getTimeSlotForProduct(slotId).setHandler(_r -> {
            if (_r.failed()) {
                promise.fail(_r.cause());
                return;
            }
            final List<JsonObject> productRelate = _r.result();
            if (productRelate.isEmpty()) {
                promise.complete(List.of());
                return;
            }
            final List<Integer> productIds = productRelate.stream().map(_rr -> _rr.getInteger("product_id")).collect(Collectors.toList());
            //获取商品信息
            productDao.list(productIds).setHandler(_rrr -> {
                if (_r.failed()) {
                    promise.fail(_rrr.cause());
                    return;
                }
                final List<JsonObject> destroy = new ArrayList<>();
                productRelate.forEach(_item -> {
                    long productId = _item.getLong("product_id");
                    final Optional<JsonObject> optional = _rrr.result().stream().filter(_a -> _a.getLong("id") == productId).findAny();
                    if (optional.isEmpty()) {
                        destroy.add(_item);
                    } else {
                        _item.put("product", optional.get());
                    }
                });
                //去除无效商品
                destroy.forEach(productRelate::remove);
                promise.complete(productRelate);
            });
        });
        return promise.future();
    }
}
