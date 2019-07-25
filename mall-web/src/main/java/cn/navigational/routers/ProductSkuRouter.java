package cn.navigational.routers;


import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductSkuService;
import cn.navigational.service.impl.ProductSkuServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/productSku")
public class ProductSkuRouter extends RouterVerticle {

    private ProductSkuService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new ProductSkuServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/sku", description = "获取商品库存", method = HttpMethod.GET)
    public Future<JsonObject> sku(JsonObject obj) {
        return service.sku(obj);
    }
}
