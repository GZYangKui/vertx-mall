package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.Optional;

public class ProductAttributeDao extends BaseDao {
    public ProductAttributeDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> getAttrCateList(long pageNum, long pageSize) {
        Tuple tuple = Tuple.tuple();
        String sql = "SELECT id,name,attribute_count AS \"attributeCount\",param_count \"paramCount\"" +
                " FROM product_attribute_category";
        if (pageNum != -1 && pageSize != -1) {
            sql += " LIMIT $1 OFFSET $2";
            tuple.addValue(pageSize);
            tuple.addValue((pageNum - 1) * pageSize);
        }
        return tuple.size() > 0 ? executeQuery(sql, tuple) : executeQuery(sql);
    }

    public Future<Long> countAttrCate() {
        String sql = "SELECT count(id) FROM product_attribute_category";
        return count(sql);
    }

    public Future<Optional<JsonObject>> findCategory(String cateName) {
        String sql = "SELECT id,name,attribute_count AS \"attributeCount\",param_count \"paramCount\"" +
                "FROM product_attribute_category WHERE name=$1";
        return findAny(sql, Tuple.of(cateName));
    }

    public Future<Integer> createCatefory(String cateName) {
        String sql = "INSERT INTO product_attribute_category(name) VALUES($1)";
        return executeUpdate(sql, Tuple.of(cateName));
    }

    public Future<List<JsonObject>> listAttr(int cId, int type, Paging paging) {
        String sql = "SELECT id,product_attribute_category_id AS \"productProductCategoryId\"," +
                "name,select_type AS \"selectType\",input_type AS \"inputType\",input_list AS \"inputList\"," +
                "sort,filter_type AS \"filterType\",search_type AS \"searchType\",related_status AS \"relatedStatus\"," +
                "hand_add_status AS \"handAddStatus\",type FROM product_attribute WHERE product_attribute_category_id=$1";
        return executeQuery(sql, Tuple.of(cId));
    }

    public Future<Long> countAttr(int cId, int type) {
        String sql = "SELECT COUNT(id) FROM product_attribute WHERE product_attribute_category_id=$1 AND type=$2";
        return countWithParam(sql, Tuple.of(cId, type));
    }
}
