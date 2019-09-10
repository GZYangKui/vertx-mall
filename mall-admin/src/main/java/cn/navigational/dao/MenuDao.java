package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MenuDao extends BaseDao {
    public MenuDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
