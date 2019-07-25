package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/product")
public class ProductRouter extends RouterVerticle {
    private ProductService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new ProductServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取商品列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
