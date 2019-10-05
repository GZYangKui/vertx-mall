package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.query.ProductCateQueryParam;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;
import static cn.navigational.utils.StringUtils.*;

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

    @RouterMapping(api = "/list", description = "获取商品分类")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        String var1 = request.getSingleRequestParam("pageSize");
        String var2 = request.getSingleRequestParam("pageIndex");
        String var3 = request.getSingleRequestParam("parentId");

        int pageIndex = 1;
        int pageSize = 10;
        if (isEmpty(var3)) {
            response.complete(responseFailed("请指定父级分类id", 200));
            return;
        }
        int parentId = Integer.parseInt(var3);
        if (!isEmpty(var1)) {
            pageSize = Integer.parseInt(var1);
        }
        if (!isEmpty(var2)) {
            pageIndex = Integer.parseInt(var2);
        }
        service.list(parentId, pageIndex, pageSize).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类列表发生错误", 200));
                return;
            }
            List<JsonObject> list = ar.result();
            service.getCateNum(parentId).setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("获取分类列表发生错误", 200));
                    return;
                }
                JsonObject data = responseSuccessJson(list).put(TOTAL, arr.result());
                response.complete(data);
            });
        });
    }

    @RouterMapping(api = "/list/withChildren", description = "获取商品列表和子分类")
    public void listWithChildren(final EBRequest request, final Promise<JsonObject> response) {
        service.listWithChildren().setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类及其子分类失败", 200));
                return;
            }
            response.complete(responseSuccessJson(ar.result()));
        });

    }

    @RouterMapping(api = "/update/navStatus", method = HttpMethod.POST, description = "更新分类导航显示状态")
    public void updateNavStatus(final EBRequest request, final Promise<JsonObject> response) {
        List<Integer> ids = getIdFromQuery(request.getSingleRequestParam("ids"));
        String var1 = request.getSingleRequestParam("navStatus");
        if (isEmpty(var1) || ids.isEmpty()) {
            response.complete(responseFailed("缺失请求参数", 200));
            return;
        }
        int navStatus = Integer.parseInt(var1);
        service.updateNavStatus(ids, navStatus).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("导航状态更新失败", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });

    }

    @RouterMapping(api = "/update/showStatus", method = HttpMethod.POST, description = "更新分类显示状态")
    public void updateShowStatus(final EBRequest request, final Promise<JsonObject> response) {
        List<Integer> ids = getIdFromQuery(request.getSingleRequestParam("ids"));
        String var1 = request.getSingleRequestParam("showStatus");
        if (isEmpty(var1) || ids.isEmpty()) {
            response.complete(responseFailed("缺失请求参数", 200));
            return;
        }
        int showStatus = Integer.parseInt(var1);
        service.updateShowStatus(ids, showStatus).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("显示状态更新失败", 200));
                return;
            }
            response.complete(responseSuccessJson());
        });
    }
}
