package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductSkuDao extends BaseDao {
    public ProductSkuDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> getSku(int productId) {
        final String sql = "SELECT * FROM sku_stock WHERE product_id=$1";
        return executeQuery(sql, Tuple.of(productId));
    }
}
