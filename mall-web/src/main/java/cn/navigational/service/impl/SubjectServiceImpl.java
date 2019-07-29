package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.SubjectDao;
import cn.navigational.dao.impl.SubjectDaoImpl;
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

public class SubjectServiceImpl extends BaseService implements SubjectService {
    private final SubjectDao dao = new SubjectDaoImpl(vertx, config);

    public SubjectServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();

        //分页查询参数
        final Paging page = getPaging(obj);

        //专题分类id
        final int cateId = Integer.parseInt(getQuery(obj, "cateId"));

        dao.getList(page, cateId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            final List<Integer> cateIds = list.stream().map(_r -> _r.getInteger("category_id"))
                    .distinct().collect(Collectors.toList());
            if (list.isEmpty() || cateIds.isEmpty()) {
                promise.complete(responseSuccessJson(List.of()));
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
                promise.complete(responseSuccessJson(temp));
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> detail(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        final int subjectId = Integer.parseInt(getQuery(obj, "subjectId"));
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
    public Future<JsonObject> cateList(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.getSubjectCateList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
        return promise.future();
    }
}
