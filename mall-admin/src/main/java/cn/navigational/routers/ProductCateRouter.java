package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.query.ProductCateQueryParam;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;
import static cn.navigational.utils.StringUtils.jsonNonEmpty;

/**
 * 商品分类管理
 *
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/productCategory")
public class ProductCateRouter extends RouterVerticle {
    private ProductCateService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductCateServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取商品分类")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        ProductCateQueryParam param = getParam(request.getQuery());
        service.list(param).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类列表发生错误", 200));
                return;
            }
            List<JsonObject> list = ar.result();
            service.getCateNum(param).setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("获取分类列表发生错误", 200));
                    return;
                }
                JsonObject data = responseSuccessJson(list).put(TOTAL, arr.result());
                response.complete(data);
            });
        });
    }

    @RequestMapping(api = "/list/withChildren", description = "获取商品列表和子分类")
    public void listWithChildren(final EBRequest request, final Promise<JsonObject> response) {
        ProductCateQueryParam param = new ProductCateQueryParam();
        param.setPageSize(1000);
        param.setPageNum(1);
        service.listWithChildren(param).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类及其子分类失败", 200));
                return;
            }
            response.complete(responseSuccessJson(ar.result()));
        });

    }

    /**
     * 生成查询参数
     *
     * @param query 请求参数
     * @return {@link ProductCateQueryParam}
     */
    private ProductCateQueryParam getParam(JsonObject query) {
        ProductCateQueryParam param = new ProductCateQueryParam();
        if (jsonNonEmpty("keyword", query))
            param.setKeyword(query.getString("keyword"));
        if (jsonNonEmpty("pageNum", query))
            param.setPageNum(Integer.parseInt(query.getString("pageNum")));
        if (jsonNonEmpty("pageSize", query))
            param.setPageSize(Integer.parseInt(query.getString("pageSize")));
        return param;
    }
}
