package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle()
@Router(api = "/api/productCate")
public class ProductCateRouter extends RouterVerticle {
    private ProductCateService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductCateServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/list", method = HttpMethod.GET, description = "获取分类列表")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.list(), promise);
    }

}
