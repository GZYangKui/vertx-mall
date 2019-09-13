package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.query.ProductQueryParamList;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;
import static cn.navigational.utils.StringUtils.jsonNonEmpty;

/**
 * 商品管理路由
 *
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/product")
public class ProductRouter extends RouterVerticle {
    private ProductService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取商品列表")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        ProductQueryParamList paramList = getParam(request.getQuery());

        service.list(paramList).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取商品列表失败", 200));
                return;
            }
            List<JsonObject> list = ar.result();
            //统计商品数量
            service.getProductNum(paramList).setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("获取商品列表失败", 200));
                    return;
                }
                long sum = arr.result();
                JsonObject resut = responseSuccessJson(list).put(TOTAL, sum);
                response.complete(resut);
            });
        });
    }

    /**
     *
     * 生成查询参数
     * @param quary q请求查询参数
     * @return {@link ProductQueryParamList}
     */
    private ProductQueryParamList getParam(JsonObject quary) {
        ProductQueryParamList param = new ProductQueryParamList();
        if (jsonNonEmpty("brandId", quary))
            param.setBrandId(Integer.parseInt(quary.getString("brandId")));
        if (jsonNonEmpty("keyword", quary))
            param.setKeyword(quary.getString("keyword"));
        if (jsonNonEmpty("pageNum", quary))
            param.setPageNum(Integer.parseInt(quary.getString("pageNum")));
        if (jsonNonEmpty("pageSize", quary))
            param.setPageSize(Integer.parseInt(quary.getString("pageSize")));
        if (jsonNonEmpty("productCategoryId", quary))
            param.setProductCategoryId(Integer.parseInt(quary.getString("productCategoryId")));
        if (jsonNonEmpty("productSn", quary))
            param.setProductSn(quary.getString("productSn"));
        if (jsonNonEmpty("publishStatus", quary))
            param.setPublishStatus(Integer.parseInt(quary.getString("publishStatus")));
        if (jsonNonEmpty("verifyStatus", quary))
            param.setVerifyStatus(Integer.parseInt(quary.getString("verifyStatus")));
        return param;
    }
}
