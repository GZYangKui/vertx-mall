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
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.tuple();

        sb.append("SELECT id, brand_id AS \"brandId\",product_category_id AS \"productCategoryId\"," +
                "title,pic,product_sn AS \"productSn\",delete_status AS \"deleteStatus\"," +
                "publish_status AS \"publishStatus\",new_status AS \"newStatus\",recommend_status AS \"recommendStatus\"," +
                "verify_status AS \"verifyStatus\",price,sale,brand_name AS \"brandName\"," +
                "product_category_name AS \"productCategoryName\",subtitle AS \"subTitle\"," +
                "sort,description,album_pics AS \"albumPics\",services,gift_growth AS \"goftGrowth\"," +
                "gift_point AS \"giftPoint\",use_point_limit AS \"usePointLimit\",original_price" +
                " AS \"originalPrice\" FROM product WHERE 1=1");

        int flag = pingSql(sb, tuple, paramList);

        sb.append(" ORDER BY id ASC")
                .append(" LIMIT $")
                .append(flag++)
                .append(" OFFSET $")
                .append(flag);

        Paging paging = new Paging(paramList.getPageNum(), paramList.getPageSize());
        tuple.addValue(paging.getPageSize());
        tuple.addValue(paging.getInitOffset());

        return executeQuery(sb.toString(), tuple);
    }

    public Future<Long> countProduct(ProductQueryParamList paramList) {
        StringBuilder sb = new StringBuilder();
        Tuple tuple = Tuple.tuple();
        sb.append("SELECT COUNT(id) FROM product WHERE 1=1");
        int flag = pingSql(sb, tuple, paramList);
        return flag == 1 ? count(sb.toString()) : countWithParam(sb.toString(), tuple);
    }

    /**
     * 拼接sql参数
     *
     * @param sb
     * @param tuple
     * @param paramList 请求参数列表
     * @return 返回sql索引系数
     */
    private int pingSql(StringBuilder sb, Tuple tuple, ProductQueryParamList paramList) {
        int flag = 1;
        if (paramList.getVerifyStatus() != -1) {
            sb.append(" AND verify_status=$").append(flag++);
            tuple.addValue(paramList.getVerifyStatus());
        }
        if (paramList.getBrandId() != -1) {
            sb.append(" AND brand_id=$").append(flag++);
            tuple.addValue(paramList.getBrandId());
        }
        if (paramList.getProductSn() != null) {
            sb.append(" AND product_sn=$").append(flag++);
            tuple.addValue(paramList.getProductSn());
        }
        if (paramList.getPublishStatus() != -1) {
            sb.append(" AND publish_status=$").append(flag++);
            tuple.addValue(paramList.getPublishStatus());
        }
        if (paramList.getKeyword() != null) {
            sb.append(" AND title LIKE $").append(flag++);
            tuple.addValue("%" + paramList.getKeyword() + "%");
        }
        if (paramList.getProductCategoryId() != -1) {
            sb.append(" AND product_category_id=$").append(flag++);
            tuple.addValue(paramList.getProductCategoryId());
        }
        return flag;
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
