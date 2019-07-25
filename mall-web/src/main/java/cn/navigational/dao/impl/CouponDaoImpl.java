package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.CouponDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class CouponDaoImpl extends BaseDao implements CouponDao {
    public CouponDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList(Paging page) {
        final String sql = "SELECT * FROM coupon WHERE end_time > (SELECT now()) AND start_time<(SELECT now()) LIMIT $1 OFFSET $2";
        return executeQuery(sql, Tuple.of(page.getPageSize(), page.getInitOffset()));
    }
}
