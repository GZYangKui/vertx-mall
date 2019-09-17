package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class PreferenceAreaDao extends BaseDao {
    public PreferenceAreaDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> getAllPreferenceArea() {
        String sql = "SELECT id,title,sub_title AS \"subTitle\",pic,sort,show_status AS \"showStatus\"" +
                " FROM preference_area";
        return executeQuery(sql);
    }
}
