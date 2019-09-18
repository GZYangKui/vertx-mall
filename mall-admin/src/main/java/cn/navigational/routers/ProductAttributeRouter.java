package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.ProductAttributeService;
import cn.navigational.service.impl.ProductAttributeServiceImpl;
import cn.navigational.utils.Assert;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.util.Optional;

import static cn.navigational.config.Constants.TOTAL;
import static cn.navigational.utils.Assert.isEmpty;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

/**
 * 商品属性管理接口
 *
 * @author YangKui
 * @since 1.0
 */

@Verticle
@Router(api = "/api/productAttribute")
public class ProductAttributeRouter extends RouterVerticle {
    private ProductAttributeService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductAttributeServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/category/list", description = "获取属性分类")
    public void listAttributeCategory(final EBRequest request, final Promise<JsonObject> response) {
        JsonObject query = request.getQuery();
        long pageNum = -1;
        long pageSize = -1;
        if (query.containsKey("pageNum")) {
            pageNum = Long.parseLong(query.getString("pageNum"));
        }
        if (query.containsKey("pageSize")) {
            pageSize = Long.parseLong(query.getString("pageSize"));
        }
        service.listCategory(pageNum, pageSize).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取分类列表失败", 200));
                return;
            }
            JsonObject result = responseSuccessJson(ar.result());
            service.countAttrCate().setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("获取分类列表失败", 200));
                    return;
                }
                result.put(TOTAL, arr.result());
                response.complete(result);
            });
        });
    }

    @RouterMapping(api = "/category/create", method = HttpMethod.POST, description = "创建分类")
    public void categoryCreate(final EBRequest request, final Promise<JsonObject> response) {
        String name = request.getSingleRequestParam("name");
        if (isEmpty(name)) {
            response.complete(responseFailed("分类名称不能为空", 200));
            return;
        }
        service.categoryDetail(name).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("新增分类失败", 200));
                return;
            }
            Optional<JsonObject> optional = ar.result();
            if (optional.isPresent()) {
                response.complete(responseFailed("分类名称已经存在", 200));
                return;
            }
            service.createCategory(name).setHandler(arr -> {
                if (arr.failed()) {
                    response.complete(responseFailed("新增分类失败", 200));
                    return;
                }
                response.complete(responseSuccessJson());
            });
        });
    }
}
