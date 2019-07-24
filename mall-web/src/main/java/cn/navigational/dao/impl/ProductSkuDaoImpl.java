package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.ProductSkuDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductSkuDaoImpl extends BaseDao implements ProductSkuDao {
    public ProductSkuDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getSku(int productId) {
        final String sql = "SELECT * FROM sku_stock WHERE product_id=$1";
        return executeQuery(sql, Tuple.of(productId));
    }
}
