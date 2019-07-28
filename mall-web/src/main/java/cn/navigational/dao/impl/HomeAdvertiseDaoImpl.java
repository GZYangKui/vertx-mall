package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.HomeAdvertiseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class HomeAdvertiseDaoImpl extends BaseDao implements HomeAdvertiseDao {
    public HomeAdvertiseDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList(int type) {
        final String sql = "SELECT * FROM home_advertise WHERE start_time < (SELECT now()) AND end_time>(SELECT now()) AND status=$1 AND type=$2 ORDER BY sort DESC";
        return executeQuery(sql, Tuple.of(1,type));
    }
}
