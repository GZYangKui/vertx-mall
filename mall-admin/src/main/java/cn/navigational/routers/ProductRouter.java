package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.query.ProductQueryParamList;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @RouterMapping(api = "/list", description = "获取商品列表")
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

    @RouterMapping(api = "/update/publishStatus", method = HttpMethod.POST, description = "更改商品发布状态")
    public void publishStatus(final EBRequest request, Promise<JsonObject> response) {

        int var2 = Integer.parseInt(request.getQuery("publishStatus"));

        service.updatePublishStatus(getProductIdFromQuery(request), var2).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("更新状态失败", 200));
                return;
            }
            response.complete(ar.result() > 0
                    ? responseSuccessJson()
                    : responseFailed("更新失败", 200));
        });
    }

    @RouterMapping(api = "/update/newStatus", method = HttpMethod.POST, description = "更改商品新品状态")
    public void newStatus(final EBRequest request, Promise<JsonObject> response) {
        int var2 = Integer.parseInt(request.getQuery("newStatus"));

        service.updateNewStatus(getProductIdFromQuery(request), var2).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("更新新品状态失败", 200));
                return;
            }
            response.complete(ar.result() > 0
                    ? responseSuccessJson()
                    : responseFailed("更新失败", 200));
        });
    }

    @RouterMapping(api = "/update/recommendStatus", method = HttpMethod.POST, description = "更改商品推荐状态")
    public void recommendStatus(final EBRequest request, Promise<JsonObject> response) {
        int var2 = Integer.parseInt(request.getQuery("recommendStatus"));
        service.updateRecommendStatus(getProductIdFromQuery(request), var2).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("更新推荐状态失败", 200));
                return;
            }
            response.complete(ar.result() > 0
                    ? responseSuccessJson()
                    : responseFailed("更新失败", 200));
        });
    }

    /**
     * 从请求url中获取商品id列表
     *
     * @param request {@link EBRequest}
     * @return 返回id列表集合
     */
    private List<Integer> getProductIdFromQuery(final EBRequest request) {
        String var1 = request.getQuery("ids");
        List<Integer> ids = new ArrayList<>();
        if (var1.contains(",")) {
            ids = Arrays.stream(var1.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } else {
            ids.add(Integer.parseInt(var1));
        }
        return ids;
    }

    /**
     * 生成查询参数
     *
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
