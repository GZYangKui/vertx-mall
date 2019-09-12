package cn.navigational.service.impl;

import cn.navigational.dao.SystemDao;
import cn.navigational.service.SystemService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class SystemServiceImpl implements SystemService {
    private SystemDao dao;

    public SystemServiceImpl(Vertx vertx, JsonObject config) {
        dao = new SystemDao(vertx,config);
    }
}
