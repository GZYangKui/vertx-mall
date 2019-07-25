package cn.navigational.routers;


import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductSkuService;
import cn.navigational.service.impl.ProductSkuServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api="/api/productSku")
public class ProductSkuRouter extends RouterVerticle {

    private ProductSkuService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new ProductSkuServiceImpl(vertx, config());
    }

    @Override
    protected Future<JsonObject> dispatch(String action, JsonObject data) {
        final Future<JsonObject> future;
        if (action.equals("/sku")) {
            future = sku(data);
        } else {
            future = notFound(action);
        }
        return future;
    }

    private Future<JsonObject> sku(JsonObject obj) {
        return service.sku(obj);
    }
}
