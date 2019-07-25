package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class CouponHistoryDaoImpl extends BaseDao implements  CouponHistoryDao{
    public CouponHistoryDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList(long userId, Paging page) {
        final String sql = "SELECT * FROM coupon_history WHERE member_id=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(userId, page.getPageSize(), page.getInitOffset()));
    }

    @Override
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

    @Override
    public Future<List<Integer>> deleteHistory(JsonArray ids) {
        return null;
    }
}
