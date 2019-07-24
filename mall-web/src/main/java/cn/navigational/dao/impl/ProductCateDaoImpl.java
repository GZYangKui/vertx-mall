package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.ProductCateDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;


public class ProductCateDaoImpl extends BaseDao implements ProductCateDao {
    public ProductCateDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getCateList(Paging page) {
        final String sql = "SELECT * FROM product_category WHERE show_status=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(1, page.getPageSize(), page.getInitOffset()));
    }
}
