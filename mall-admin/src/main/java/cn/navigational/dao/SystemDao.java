package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class SystemDao extends BaseDao {
    public SystemDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
