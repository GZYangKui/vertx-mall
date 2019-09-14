package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.query.ProductCateQueryParam;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductCateDao extends BaseDao {
    public ProductCateDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> list(ProductCateQueryParam param) {
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.of(1);
        sb.append("SELECT  id,parent_id AS \"parentId\",name,product_count AS \"productCount\"," +
                "show_status AS showStatus,icon,description,sort FROM product_category WHERE 1=$1");
        if (param.getKeyword() != null) {
            sb.append(" AND name LIKE $2");
            tuple.addValue("%"+param.getKeyword()+"%");
        }
        sb.append(" LIMIT ").append(
                param.getKeyword() != null ? "$3 OFFSET $4" : "$2 OFFSET $3");
        Paging paging = getPaging(param.getPageNum(), param.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sb.toString(), tuple);
    }

    public Future<Long> count(ProductCateQueryParam param) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) FROM product_category");
        Tuple tuple = Tuple.tuple();
        if (param.getKeyword() != null) {
            sb.append(" WHERE name LIKE $1");
            tuple.addValue("%"+param.getKeyword()+"%");
        }
        String sql = sb.toString();
        System.out.println(sql);
        return tuple.size() > 0 ? countWithParam(sql, tuple) : count(sql);
    }
}
