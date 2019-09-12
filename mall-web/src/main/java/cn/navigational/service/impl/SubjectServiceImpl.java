package cn.navigational.service.impl;


import cn.navigational.dao.SubjectDao;
import cn.navigational.model.Paging;
import cn.navigational.service.SubjectService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao dao;

    public SubjectServiceImpl(Vertx vertx, JsonObject config) {
        dao = new SubjectDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging, int cateId) {
        final Promise<List<JsonObject>> promise = Promise.promise();

        dao.getList(paging, cateId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            final List<Integer> cateIds = list.stream().map(_r -> _r.getInteger("category_id"))
                    .distinct().collect(Collectors.toList());
            if (list.isEmpty() || cateIds.isEmpty()) {
                promise.complete(List.of());
                return;
            }
            dao.getSubjectCateInfo(cateIds).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                final List<JsonObject> temp = new ArrayList<>();
                _rr.result().forEach(_t -> {
                    list.stream().filter(_tt -> _tt.getInteger("category_id") == _t.getInteger("id")).forEach(_tt -> {
                        _tt.put("categoryInfo", _t);
                        temp.add(_tt);
                    });
                });
                promise.complete(temp);
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> detail(int subjectId) {
        final Promise<JsonObject> promise = Promise.promise();

        dao.getSubjectInfo(subjectId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            if (_rs.result().isEmpty()) {
                promise.complete(responseFailed("专题信息不存在", 200));
                return;
            }
            final JsonObject item = _rs.result().get();
            final int cateId = item.getInteger("category_id");
            dao.getSubjectCateInfo(List.of(cateId)).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                if (_rr.result().isEmpty()) {
                    promise.complete(responseFailed("专题信息不存在", 200));
                    return;
                }
                item.put("categoryInfo", _rr.result().get(0));
                promise.complete(responseSuccessJson(item));
            });
        });
        return promise.future();
    }

    @Override
    public Future<List<JsonObject>> cateList() {
        final Promise<List<JsonObject>> promise = Promise.promise();
        dao.getSubjectCateList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }
}
