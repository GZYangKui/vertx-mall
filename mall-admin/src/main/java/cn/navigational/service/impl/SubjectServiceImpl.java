package cn.navigational.service.impl;

import cn.navigational.dao.PreferenceAreaDao;
import cn.navigational.dao.SubjectDao;
import cn.navigational.service.PreferenceAreaService;
import cn.navigational.service.SubjectService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao dao;
    private static final Logger logger = LogManager.getLogger(SubjectServiceImpl.class);

    public SubjectServiceImpl(Vertx vertx, JsonObject config) {
        dao = new SubjectDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> listAllSubject() {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.getAllSubject().setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取专题失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }
}
