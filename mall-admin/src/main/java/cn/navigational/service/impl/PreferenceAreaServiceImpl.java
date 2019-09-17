package cn.navigational.service.impl;

import cn.navigational.dao.PreferenceAreaDao;
import cn.navigational.service.PreferenceAreaService;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class PreferenceAreaServiceImpl implements PreferenceAreaService {
    private PreferenceAreaDao dao;
    private static final Logger logger = LogManager.getLogger(PreferenceAreaServiceImpl.class);

    public PreferenceAreaServiceImpl(Vertx vertx, JsonObject config) {
        dao = new PreferenceAreaDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> listAllPrefrenceArea() {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.getAllPreferenceArea().setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取优选专题失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }
}
