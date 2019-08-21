package cn.navigational.service.impl;

import cn.navigational.dao.PreferenceDao;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.impl.PreferenceDaoImpl;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.PreferenceService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceDao dao;
    private final ProductDao productDao;

    public PreferenceServiceImpl(Vertx vertx, JsonObject config) {
        dao = new PreferenceDaoImpl(vertx, config);
        productDao = new ProductDaoImpl(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging) {
        final Promise<List<JsonObject>> promise = Promise.promise();

        dao.getList(paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(List.of());
                return;
            }

            //将商品列表初始化为0
            list.forEach(_t -> _t.put("products", List.of()));

            //获取优选专题id
            final List<Integer> ids = list.stream().map(_rr -> _rr.getInteger("id")).collect(Collectors.toList());
            //获取关联商品id
            dao.getRelateProductId(ids).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                //专题关联信息
                final List<JsonObject> temp = _rr.result();

                //获取商品id
                final List<Integer> products = temp.stream().map(_r -> _r.getInteger("product_id")).collect(Collectors.toList());

                if (products.isEmpty()) {
                    promise.complete(list);
                    return;
                }

                //获取商品信息
                productDao.list(products).setHandler(_rrr -> {
                    if (_rrr.failed()) {
                        promise.fail(_rrr.cause());
                        return;
                    }
                    list.forEach(_t -> {
                        final List<JsonObject> o = temp.stream()
                                .filter(_tt -> _tt.getInteger("preference_area_id") == _t.getInteger("id"))
                                .collect(Collectors.toList());

                        if (o.isEmpty()) {
                            _t.put("products", List.of());
                            return;
                        }

                        final List<JsonObject> p = _rrr.result().stream()
                                .filter(_tt -> o.stream()
                                        .map(_k -> _k.getInteger("product_id"))
                                        .collect(Collectors.toList()).contains(_tt.getInteger("id")))
                                .collect(Collectors.toList());
                        _t.put("products", p);
                    });
                    promise.complete(list);
                });
            });
        });
        return promise.future();
    }
}
