package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttributCategory;
import cn.navigational.model.ProductAttribute;
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

    public Future<Optional<JsonObject>> findCategoryByName(String cateName) {
        String sql = "SELECT id,name,attribute_count AS \"attributeCount\",param_count \"paramCount\",version" +
                " FROM product_attribute_category WHERE name=$1";
        return findAny(sql, Tuple.of(cateName));
    }

    public Future<Optional<JsonObject>> findCategoryById(int cateId) {
        String sql = "SELECT id,name,attribute_count AS \"attributeCount\",param_count \"paramCount\",version" +
                " FROM product_attribute_category WHERE id=$1";
        return findAny(sql, Tuple.of(cateId));
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

    public Future<Optional<JsonObject>> findAttribute(ProductAttribute attribute) {
        String sql = "SELECT id,product_attribute_category_id AS \"productProductCategoryId\"," +
                "name,select_type AS \"selectType\",input_type AS \"inputType\",input_list AS \"inputList\"," +
                "sort,filter_type AS \"filterType\",search_type AS \"searchType\",related_status AS \"relatedStatus\"," +
                "hand_add_status AS \"handAddStatus\",type FROM product_attribute WHERE product_attribute_category_id=$1" +
                " AND name=$2 AND type=$3";
        Tuple tuple = Tuple.tuple();
        tuple.addValue(attribute.getProductAttributeCategoryId());
        tuple.addValue(attribute.getName());
        tuple.addValue(attribute.getType());
        return findAny(sql, tuple);
    }

    public Future<Integer> updateCateAttrNum(ProductAttributCategory category, int type, int val) {
        String sql = "UPDATE product_attribute_category SET";
        if (type == 0) {
            sql += " attribute_count=$1";
        } else {
            sql += " param_count=$1";
        }
        sql += ",version=$2 WHERE id=$3 AND version=$4";
        Tuple tuple = Tuple.tuple();
        tuple.addValue(type == 0 ? category.getAttributeCount() + val : category.getParamCount() + val);
        tuple.addValue(category.getVersion() + 1);
        tuple.addValue(category.getId());
        tuple.addValue(category.getVersion());
        return executeUpdate(sql, tuple);
    }

    public Future<Integer> createAttribute(ProductAttribute attribute) {
        String sql = "INSERT INTO  product_attribute(product_attribute_category_id," +
                "name,select_type,input_type,input_list,sort,filter_type,search_type," +
                "related_status,hand_add_status,type) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11)";
        Tuple tuple = Tuple.tuple();
        tuple.addValue(attribute.getProductAttributeCategoryId());
        tuple.addValue(attribute.getName());
        tuple.addValue(attribute.getSelectType());
        tuple.addValue(attribute.getInputType());
        tuple.addValue(attribute.getInputList());
        tuple.addValue(attribute.getSort());
        tuple.addValue(attribute.getFilterType());
        tuple.addValue(attribute.getSearchType());
        tuple.addValue(attribute.getRelatedStatus());
        tuple.addValue(attribute.getHandAddStatus());
        tuple.addValue(attribute.getType());
        return executeUpdate(sql, tuple);
    }
}
