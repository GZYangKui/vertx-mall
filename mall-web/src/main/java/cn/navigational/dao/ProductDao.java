package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductDao extends BaseDao {
    public ProductDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> list(Paging paging) {
        final String sql = "SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 LIMIT $3 OFFSET $4";
        return executeQuery(sql, Tuple.of(0, 1, paging.getPageSize(), paging.getInitOffset()));
    }


    public Future<List<JsonObject>> list(List<Integer> productIds) {
        final StringBuilder sb = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sb.append("SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 AND id IN(");
        tuple.addValue(0);
        tuple.addValue(1);
        pingIn(sb,tuple,productIds,3);
        return executeQuery(sb.toString(), tuple);
    }


    public Future<List<JsonObject>> getRecommend(Paging paging) {
        final String sql = "SELECT * FROM product WHERE delete_status=$1 AND publish_status=$2 AND recommend_status=$3 LIMIT $4 OFFSET $5";
        return executeQuery(sql, Tuple.of(0,1,1,paging.getPageSize(), paging.getInitOffset()));
    }
}
