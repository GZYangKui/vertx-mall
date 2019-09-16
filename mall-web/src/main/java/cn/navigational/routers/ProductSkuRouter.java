package cn.navigational.routers;


import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.ProductSkuService;
import cn.navigational.service.impl.ProductSkuServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/productSku")
public class ProductSkuRouter extends RouterVerticle {

    private ProductSkuService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductSkuServiceImpl(vertx, config());
    }


    @RouterMapping(api = "/info", description = "获取商品库存", method = HttpMethod.GET)
    public void sku(final EBRequest request, final Promise<JsonObject> promise) {
        final int productId = Integer.parseInt(request.getQuery("productId"));
        futureStatus(service.sku(productId), promise);
    }
}
