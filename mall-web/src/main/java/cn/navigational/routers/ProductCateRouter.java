package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;
import io.vertx.core.Future;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle()
@Router(api = "/api/productCate")
public class ProductCateRouter extends RouterVerticle {
    private ProductCateService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductCateServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list",method = HttpMethod.GET,description = "获取分类列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
