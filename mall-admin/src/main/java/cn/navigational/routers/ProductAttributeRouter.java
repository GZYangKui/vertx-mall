package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.ProductAttributeService;
import cn.navigational.service.impl.ProductAttributeServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

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
    public void listAttributeCategory(final EBRequest request, final Promise<JsonObject> promise) {

    }
}
