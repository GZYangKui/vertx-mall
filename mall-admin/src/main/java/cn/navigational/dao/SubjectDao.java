package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class SubjectDao extends BaseDao {
    public SubjectDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> getAllSubject() {
        String sql = "SELECT id,category_id AS \"categoryId\",pic,product_count AS \"productCount\"," +
                "recommend_status AS \"recommendStatus\",create_time AS \"createTime\",description," +
                "show_status AS \"showStatus\",collect_count AS \"collectCount\",title," +
                "read_count AS \"readCount\",comment_count AS \"commentCount\",album_pics AS albumPics," +
                "show_status AS \"showStatus\",forward_count AS \"forwardCount\"" +
                " FROM mall_subject";
        return executeQuery(sql);
    }
}
