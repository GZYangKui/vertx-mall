package cn.navigational.service.impl;

import cn.navigational.dao.ProductCateDao;
import cn.navigational.service.ProductCateService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;




public class ProductCateServiceImpl implements ProductCateService {
    private final ProductCateDao dao;

    public ProductCateServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductCateDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list() {
        final Promise<List<JsonObject>> promise = Promise.promise();

        dao.getCateList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();

            if (list.isEmpty()) {
                promise.complete(List.of());
                return;
            }

            //一级分类
            final List<JsonObject> categories = list.stream().filter(_r -> _r.getInteger("parent_id") == 0).collect(Collectors.toList());

            //将二级分类放到一级分类之中
            categories.forEach(_r -> {
                final List<JsonObject> children = list.stream().filter(_rr -> _rr.getInteger("parent_id") == _r.getInteger("id")).collect(Collectors.toList());
                _r.put("children", children);
            });

            //倒叙排列
            categories.sort(Comparator.comparing(_rr -> ((JsonObject) _rr).getInteger("sort")).reversed());

            promise.complete(categories);
        });

        return promise.future();

    }
}
