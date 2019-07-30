package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.HomeSubjectDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class HomeSubjectDaoImpl extends BaseDao implements HomeSubjectDao {

    public HomeSubjectDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList() {
        final String sql = "SELECT * FROM home_recommend_subject WHERE recommend_status=$1 ORDER BY sort DESC";
        return executeQuery(sql, Tuple.of(1));
    }

    @Override
    public Future<List<JsonObject>> getSubjectList(List<Integer> ids) {
        final StringBuilder sb = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sb.append("SELECT * FROM mall_subject WHERE id IN(");
        pingIn(sb, tuple, ids,1);
        return executeQuery(sb.toString(), tuple);
    }
}
