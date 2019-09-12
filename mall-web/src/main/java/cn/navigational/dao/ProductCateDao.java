package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;


public class ProductCateDao extends BaseDao{
    public ProductCateDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> getCateList() {
        final String sql = "SELECT * FROM product_category WHERE show_status=$1";
        return executeQuery(sql, Tuple.of(1));
    }
}
