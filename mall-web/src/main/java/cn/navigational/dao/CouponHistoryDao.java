package cn.navigational.dao;

import cn.navigational.base.BaseDao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class CouponHistoryDao extends BaseDao {
    public CouponHistoryDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> getList(long userId, Paging page) {
        final String sql = "SELECT * FROM coupon_history WHERE member_id=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(userId, page.getPageSize(), page.getInitOffset()));
    }


    public Future<List<JsonObject>> getCoupon(List<Integer> ids) {
        final StringBuilder sql = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sql.append("SELECT * FROM coupon WHERE id IN(");
        for (int i = 0; i < ids.size(); i++) {
            final int index =i+1;
            if (i!=ids.size()-1) {
                sql.append("$").append(index).append(",");
            }else {
                sql.append("$").append(index);
            }
            tuple.addValue(ids.get(i));
        }
        sql.append(")");
        return executeQuery(sql.toString(), tuple);
    }


    public Future<Integer> deleteHistory(JsonArray ids) {
        final String sql = "DELETE FROM coupon_history WHERE id=$1";
        final List<Tuple> tuples = new ArrayList<>();
        ids.forEach(_t->tuples.add(Tuple.of(_t)));
        return batchUpdate(sql,tuples);
    }
}
