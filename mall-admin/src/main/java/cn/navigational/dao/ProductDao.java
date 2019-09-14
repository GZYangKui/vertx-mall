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

        sql.append("SELECT id, brand_id AS \"brandId\",product_category_id AS \"productCategoryId\"," +
                "title,pic,product_sn AS \"productSn\",delete_status AS \"deleteStatus\"," +
                "publish_status AS \"publishStatus\",new_status AS \"newStatus\",recommend_status AS \"recommendStatus\"," +
                "verify_status AS \"verifyStatus\",price,sale,brand_name AS \"brandName\"," +
                "product_category_name AS \"productCategoryName\",subtitle AS \"subTitle\"," +
                "sort,description,album_pics AS \"albumPics\",services,gift_growth AS \"goftGrowth\"," +
                "gift_point AS \"giftPoint\",use_point_limit AS \"usePointLimit\",original_price" +
                " AS \"originalPrice\" FROM product");

        sql.append(" ORDER BY id ASC LIMIT $1 OFFSET $2");

        Paging paging = new Paging(paramList.getPageNum(), paramList.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sql.toString(), tuple);
    }

    public Future<Long> countProduct(ProductQueryParamList paramList) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM product");

        return count(sb.toString());
    }

    /**
     * 更新商品状态(新品/推荐/上线)
     *
     * @param ids        商品id列表
     * @param status     商品状态
     * @param statusName 数据库状态字段名称
     * @return 异步返回受影响行数
     */
    public Future<Integer> updateProductStatus(List<Integer> ids, int status, String statusName) {
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.of(status);
        sb.append("UPDATE product SET ");
        sb.append(statusName).append("=$1").append(" ");
        sb.append("WHERE id IN(");
        pingIn(sb, tuple, ids, 2);
        return executeUpdate(sb.toString(), tuple);
    }
}
