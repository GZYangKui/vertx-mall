package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ProductDao extends BaseDao {
    public ProductDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
