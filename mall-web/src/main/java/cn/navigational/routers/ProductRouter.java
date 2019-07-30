package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/product")
public class ProductRouter extends RouterVerticle {
    private ProductService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductServiceImpl(vertx, config());
    }


    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取商品列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    @RequestMapping(api = "/todayRecommend",method = HttpMethod.GET,description = "今日推荐")
    public Future<JsonObject> todayRecommend(JsonObject obj){return service.todayRecommend(obj);}
}
