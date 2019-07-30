package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.ProductService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductDao dao;

    public ProductServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.list(getPaging(obj)).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> todayRecommend(JsonObject object) {
        final Promise<JsonObject> promise = Promise.promise();
        final Paging page = getPaging(object);
        dao.getTodayRecommend(page).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(responseSuccessJson(List.of()));
                return;
            }
            //获取产品列表
            final List<Integer> productIds = list.stream().map(_r -> _r.getInteger("product_id")).collect(Collectors.toList());
            dao.list(productIds).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                final List<JsonObject> destroy = new ArrayList<>();
                list.forEach(_r -> {
                    final long id = _r.getLong("product_id");
                    final Optional<JsonObject> optional = _rr.result().stream().filter(_rrr -> _rrr.getLong("id") == id).findAny();
                    if (optional.isEmpty()) {
                        destroy.add(_r);
                    } else {
                        _r.put("product", optional.get());
                    }
                });
                destroy.forEach(list::remove);
                promise.complete(responseSuccessJson(list));
            });
        });
        return promise.future();
    }
}
