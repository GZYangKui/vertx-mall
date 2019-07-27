package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.PreferenceDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class PreferenceDaoImpl extends BaseDao implements PreferenceDao {
    public PreferenceDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList(Paging page) {
        final String sql = "SELECT * FROM preference_area WHERE show_status=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(1, page.getPageSize(), page.getInitOffset()));
    }

    @Override
    public Future<List<JsonObject>> getRelateProductId(List<Integer> preferenceIds) {
        final StringBuilder sb = new StringBuilder("SELECT preference_area_id ,product_id FROM preference_area_product_relation WHERE preference_area_id IN(");
        final Tuple tuple = Tuple.tuple();
        for (int i = 0; i < preferenceIds.size(); i++) {
            final int index = i + 1;
            sb.append("$").append(index);
            if (i == preferenceIds.size() - 1) {
                sb.append(")");
            } else {
                sb.append(",");
            }
            tuple.addValue(preferenceIds.get(i));
        }
        return executeQuery(sb.toString(), tuple);
    }
}
