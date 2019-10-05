package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.query.ProductCateQueryParam;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class ProductCateDao extends BaseDao {
    public ProductCateDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> list(int parentId, int pageIndex, int pageSize) {
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.tuple();
        sb.append("SELECT  id,parent_id AS \"parentId\",name,product_count AS \"productCount\",nav_status AS \"navStatus\"," +
                "show_status AS showStatus,icon,description,sort FROM product_category WHERE parent_id=$1");
        sb.append(" LIMIT $2 OFFSET $3");

        tuple.addValue(parentId);
        Paging paging = getPaging(pageIndex, pageSize);
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sb.toString(), tuple);
    }

    public Future<Long> count(int parentId) {
        String sql = "SELECT count(*) FROM product_category WHERE parent_id=$1";
        Tuple tuple = Tuple.of(parentId);
        return countWithParam(sql, tuple);
    }

    public Future<List<JsonObject>> list() {
        String sql = "SELECT  id,parent_id AS \"parentId\",name,product_count AS \"productCount\",show_status AS showStatus," +
                "nav_status AS \"navStatus\",icon,description,sort FROM product_category";
        return executeQuery(sql);
    }

    public Future<Integer> updateShowStatus(List<Integer> ids, int status) {
        List<Tuple> tuples = new ArrayList<>();
        String sql = "UPDATE product_category SET show_status=$1 WHERE id=$2";
        for (Integer id : ids) {
            Tuple tuple = Tuple.tuple();
            tuple.addValue(status);
            tuple.addValue(id);
            tuples.add(tuple);
        }
        return batchUpdate(sql, tuples);
    }

    public Future<Integer> updateNavStatus(List<Integer> ids, int status) {
        List<Tuple> tuples = new ArrayList<>();
        String sql = "UPDATE product_category SET nav_status=$1 WHERE id=$2";
        for (Integer id : ids) {
            Tuple tuple = Tuple.tuple();
            tuple.addValue(status);
            tuple.addValue(id);
            tuples.add(tuple);
        }
        return batchUpdate(sql, tuples);
    }
}
