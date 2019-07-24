package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.ProductDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    public ProductDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging) {
        final String sql = "SELECT * FROM product WHERE delete_status=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(0, paging.getPageSize(), paging.getInitOffset()));
    }
}
