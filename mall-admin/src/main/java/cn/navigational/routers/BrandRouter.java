package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.query.BrandQueryParam;
import cn.navigational.model.query.ProductCateQueryParam;
import cn.navigational.service.BrandService;
import cn.navigational.service.impl.BrandServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;
import static cn.navigational.utils.StringUtils.jsonNonEmpty;

/**
 * 商品品牌管理
 *
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/brand")
public class BrandRouter extends RouterVerticle {
    private BrandService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new BrandServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取品牌列表")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        BrandQueryParam param = getParam(request.getQuery());
        service.list(param).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取品牌列表发生错误", 200));
                return;
            }
            List<JsonObject> list = ar.result();
            service.count(param).setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("获取品牌列表发生错误", 200));
                    return;
                }
                JsonObject data = responseSuccessJson(list).put(TOTAL, arr.result());
                response.complete(data);
            });
        });
    }

    /**
     * 生成查询参数
     *
     * @param query 请求参数
     * @return {@link ProductCateQueryParam}
     */
    private BrandQueryParam getParam(JsonObject query) {
        BrandQueryParam param = new BrandQueryParam();
        if (jsonNonEmpty("keyword", query))
            param.setKeyword(query.getString("keyword"));
        if (jsonNonEmpty("pageNum", query))
            param.setPageNum(Integer.parseInt(query.getString("pageNum")));
        if (jsonNonEmpty("pageSize", query))
            param.setPageSize(Integer.parseInt(query.getString("pageSize")));
        return param;
    }
}
