package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ProductAttributeDao extends BaseDao {
    public ProductAttributeDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
