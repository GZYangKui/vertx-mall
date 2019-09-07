package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class UserDao extends BaseDao {
    public UserDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
