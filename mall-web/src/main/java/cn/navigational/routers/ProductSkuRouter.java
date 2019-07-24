package cn.navigational.routers;


import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductSkuService;
import cn.navigational.service.impl.ProductSkuServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * 商品库存
 */
public class ProductSkuRouter extends RouterVerticle {

    private ProductSkuService service;

    @Override
    public void start() throws Exception {
        super.start("/api/productSku");
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
