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
                "publish_status AS \"publishStatus\",new_status AS \"newStatus\",recommend_status AS \"recommedStatus\"," +
                "verify_status AS \"verifyStatus\",price,sale,brand_name AS \"brandName\"," +
                "product_category_name AS \"productCategoryName\",subtitle AS \"subTitle\"," +
                "sort,description,album_pics AS \"albumPics\",services,gift_growth AS \"goftGrowth\"," +
                "gift_point AS \"giftPoint\",use_point_limit AS \"usePointLimit\",original_price" +
                " AS \"originalPrice\" FROM product");

        sql.append(" LIMIT $1 OFFSET $2");

        Paging paging = new Paging(paramList.getPageIndex(), paramList.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sql.toString(), tuple);
    }
}
