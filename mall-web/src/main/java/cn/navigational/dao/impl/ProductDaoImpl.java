package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.ProductDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.time.LocalDate;
import java.util.List;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    public ProductDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> list(Paging paging) {
        final String sql = "SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 LIMIT $3 OFFSET $4";
        return executeQuery(sql, Tuple.of(0, 1, paging.getPageSize(), paging.getInitOffset()));
    }

    @Override
    public Future<List<JsonObject>> list(List<Integer> productIds) {
        final StringBuilder sb = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sb.append("SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 AND id IN(");
        tuple.addValue(0);
        tuple.addValue(1);
        pingIn(sb,tuple,productIds,3);
        return executeQuery(sb.toString(), tuple);
    }

    @Override
    public Future<List<JsonObject>> getRecommend(Paging paging) {
        final String sql = "SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 AND recommend_status=$3 LIMIT $4 OFFSET $5";
        return executeQuery(sql, Tuple.of(0,1,1,paging.getPageSize(), paging.getInitOffset()));
    }
}
