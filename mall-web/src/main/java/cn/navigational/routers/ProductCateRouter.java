package cn.navigational.routers;

import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;
import io.vertx.core.Future;

import io.vertx.core.json.JsonObject;

/**
 *
 * 商品分类
 *
 */
public class ProductCateRouter extends RouterVerticle {
    private ProductCateService service;

    @Override
    public void start() throws Exception {
        start("/api/productCate");
        service = new ProductCateServiceImpl(vertx, config());
    }

    @Override
    protected Future<JsonObject> dispatch(String action, JsonObject data) {
        if (action.equals("/list")) {
            return list(data);
        } else {
            return notFound(action);
        }
    }

    private Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
