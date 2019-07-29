package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.HomeSubjectDao;
import cn.navigational.dao.impl.HomeSubjectDaoImpl;
import cn.navigational.service.HomeSubjectService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class HomeSubjectServiceImpl extends BaseService implements HomeSubjectService {

    private final HomeSubjectDao dao = new HomeSubjectDaoImpl(vertx, config);

    public HomeSubjectServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.getList().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(responseSuccessJson(List.of()));
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

                promise.complete(responseSuccessJson(list));
            });
        });
        return promise.future();
    }
}
