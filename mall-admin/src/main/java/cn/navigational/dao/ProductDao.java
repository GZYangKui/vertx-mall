package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.query.ProductQueryParamList;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class ProductDao extends BaseDao {
    public ProductDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> getProductList(ProductQueryParamList paramList) {
        StringBuilder sql = new StringBuilder();
        Tuple tuple = Tuple.tuple();

        sql.append("SELECT * FROM product");

        sql.append(" LIMIT $1 OFFSET $2");

        Paging paging = new Paging(paramList.getPageIndex(), paramList.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sql.toString(), tuple);
    }
}
