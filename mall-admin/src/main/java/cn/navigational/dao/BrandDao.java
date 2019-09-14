package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.query.BrandQueryParam;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class BrandDao extends BaseDao {
    public BrandDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> list(BrandQueryParam param) {
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.of(1);
        sb.append("SELECT  id,name,first_letter AS \"firstLetter\",sort,product_count AS \"productCount\"," +
                "show_status AS showStatus,product_comment_count AS \"productCommentCount\"," +
                "logo,big_picture AS \"bigPicture\",brand_story AS \"brandStory\",sort,version," +
                "factory_status AS \"factoryStatus\" FROM mall_brand WHERE 1=$1");
        if (param.getKeyword() != null) {
            sb.append(" AND name LIKE $2");
            tuple.addValue("%" + param.getKeyword() + "%");
        }
        sb.append(" LIMIT ").append(
                param.getKeyword() != null ? "$3 OFFSET $4" : "$2 OFFSET $3");
        Paging paging = getPaging(param.getPageNum(), param.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sb.toString(), tuple);
    }

    public Future<Long> count(BrandQueryParam param) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) FROM mall_brand");
        Tuple tuple = Tuple.tuple();
        if (param.getKeyword() != null) {
            sb.append(" WHERE name LIKE $1");
            tuple.addValue("%" + param.getKeyword() + "%");
        }
        String sql = sb.toString();
        return tuple.size() > 0 ? countWithParam(sql, tuple) : count(sql);
    }
}
