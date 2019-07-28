package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.ProductCateDao;
import cn.navigational.dao.impl.ProductCateDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.ProductCateService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


public class ProductCateServiceImpl extends BaseService implements ProductCateService {
    private final ProductCateDao dao;

    public ProductCateServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new ProductCateDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();

        dao.getCateList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();

            if (list.isEmpty()) {
                promise.complete(responseSuccessJson(List.of()));
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

            promise.complete(responseSuccessJson(categories));
        });

        return promise.future();

    }
}
