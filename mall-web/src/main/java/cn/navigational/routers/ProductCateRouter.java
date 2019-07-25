package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductCateService;
import cn.navigational.service.impl.ProductCateServiceImpl;
import io.vertx.core.Future;

import io.vertx.core.json.JsonObject;

@Verticle()
@Router(api = "/api/productCate")
public class ProductCateRouter extends RouterVerticle {
    private ProductCateService service;

    @Override
    public void start() throws Exception {
        super.start();
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
