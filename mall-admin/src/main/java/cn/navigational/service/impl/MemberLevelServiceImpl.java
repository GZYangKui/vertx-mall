package cn.navigational.service.impl;

import cn.navigational.dao.MemberLevelDao;
import cn.navigational.service.MemberLevelService;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class MemberLevelServiceImpl implements MemberLevelService {
    private MemberLevelDao dao;
    private static final Logger logger = LogManager.getLogger(MemberLevelServiceImpl.class);

    public MemberLevelServiceImpl(Vertx vertx, JsonObject config) {
        dao = new MemberLevelDao(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(int defaultStatus) {
        Promise<List<JsonObject>> promise = Promise.promise();
        dao.getList(defaultStatus).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取会员等级列表失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
    }
}
