package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import cn.navigational.model.ProductAttributCategory;
import cn.navigational.model.ProductAttribute;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.ArrayList;
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

    public Future<Integer> deleteCategory(int cateId) {
        String sql = "DELETE FROM product_attribute_category WHERE id=$1";
        return executeUpdate(sql, Tuple.of(cateId));
    }

    public Future<Integer> deleteCateAttr(int cateId) {
        String sql = "DELETE FROM product_attribute WHERE product_attribute_category_id=$1";
        return executeUpdate(sql, Tuple.of(cateId));
    }

    public Future<Integer> updateCate(int cateId, String name) {
        String sql = "UPDATE product_attribute_category SET name=$1 WHERE id=$2";
        return executeUpdate(sql, Tuple.of(name, cateId));
    }

    public Future<List<JsonObject>> findAttrById(List<Integer> ids) {
        Tuple tuple = Tuple.tuple();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id,product_attribute_category_id AS \"productAttributeCategoryId\"," +
                "name,select_type AS \"selectType\",input_type AS \"inputType\",input_list AS \"inputList\"," +
                "sort,filter_type AS \"filterType\",search_type AS \"searchType\",related_status AS \"relatedStatus\"," +
                "hand_add_status AS \"handAddStatus\",type FROM product_attribute WHERE id IN(");
        pingIn(sb, tuple, ids, 1);
        return executeQuery(sb.toString(), tuple);
    }

    public Future<Integer> deleteAttr(List<Integer> ids) {
        List<Tuple> tuples = new ArrayList<>();
        String sql = "DELETE FROM product_attribute WHERE id=$1";
        for (Integer id : ids) {
            Tuple tuple = Tuple.tuple();
            tuple.addValue(id);
            tuples.add(tuple);
        }
        return batchUpdate(sql, tuples);
    }

    public Future<Optional<JsonObject>> findAttrById(int id) {
        String sql = "SELECT id,product_attribute_category_id AS \"productAttributeCategoryId\"," +
                "name,select_type AS \"selectType\",input_type AS \"inputType\",input_list AS \"inputList\"," +
                "sort,filter_type AS \"filterType\",search_type AS \"searchType\",related_status AS \"relatedStatus\"," +
                "hand_add_status AS \"handAddStatus\",type FROM product_attribute WHERE id=$1";
        return findAny(sql, Tuple.of(id));
    }

    public Future<Integer> updateAttr(ProductAttribute attr) {
        String sql = "UPDATE product_attribute SET product_attribute_category_id=$1," +
                "name=$2,select_type=$3,input_type=$4,input_list=$5,sort=$6,filter_type=$7,search_type=$8," +
                "related_status=$9,hand_add_status=$10,type=$11 WHERE id=$12";
        Tuple tuple = Tuple.tuple();
        tuple.addValue(attr.getProductAttributeCategoryId());
        tuple.addValue(attr.getName());
        tuple.addValue(attr.getSelectType());
        tuple.addValue(attr.getInputType());
        tuple.addValue(attr.getInputList());
        tuple.addValue(attr.getSort());
        tuple.addValue(attr.getFilterType());
        tuple.addValue(attr.getSearchType());
        tuple.addValue(attr.getRelatedStatus());
        tuple.addValue(attr.getHandAddStatus());
        tuple.addValue(attr.getType());
        tuple.addValue(attr.getId());
        return executeUpdate(sql, tuple);
    }
}
