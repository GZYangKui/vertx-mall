package cn.navigational.service.impl;


import cn.navigational.dao.HomeSubjectDao;
import cn.navigational.service.HomeSubjectService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class HomeSubjectServiceImpl implements HomeSubjectService {

    private final HomeSubjectDao dao;

    public HomeSubjectServiceImpl(Vertx vertx, JsonObject config) {

        dao = new HomeSubjectDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list() {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(List.of());
                return;
            }
            final List<Integer> ids = list.stream().map(_r -> _r.getInteger("subject_id")).collect(Collectors.toList());
            dao.getSubjectList(ids).setHandler(_r -> {
                if (_r.failed()) {
                    promise.fail(_r.cause());
                    return;
                }
                final List<JsonObject> destroy = new ArrayList<>();
                list.forEach(_rr -> {
                    final Optional<JsonObject> optional = _r.result().stream()
                            .filter(_rrr -> _rrr.getInteger("id") == _rr.getInteger("subject_id"))
                            .findAny();
                    if (optional.isEmpty()) {
                        destroy.add(_rr);
                        return;
                    }
                    _rr.put("subject", optional.get());
                });
                //去除专题不存在的数据
                destroy.forEach(list::remove);

                promise.complete(list);
            });
        });
        return promise.future();
    }
}
