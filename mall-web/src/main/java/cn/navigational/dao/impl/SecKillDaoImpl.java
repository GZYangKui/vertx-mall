package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.SecKillDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class SecKillDaoImpl extends BaseDao implements SecKillDao {
    public SecKillDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getTimeSlot() {
        final String sql = "SELECT * FROM flash_promotion_session WHERE status=$1";
        return executeQuery(sql, Tuple.of(1));
    }

    @Override
    public Future<List<JsonObject>> getTimeSlotForProduct(long timeSlotId) {
        final String sql = "SELECT * FROM flash_promotion_product_relation WHERE flash_promotion_session_id = $1 ORDER BY sort DESC";
        return executeQuery(sql,Tuple.of(timeSlotId));
    }
}
