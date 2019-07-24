package cn.navigational.routers;

import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 *
 * 商品
 *
 */
public class ProductRouter extends RouterVerticle {
    private ProductService service;
    @Override
    public void start() throws Exception {
        super.start("/api/product");
        service = new ProductServiceImpl(vertx,config());
    }

    @Override
    protected Future<JsonObject> dispatch(String action,JsonObject data) {
        if (action.equals("/list")) {
            return list(data);
        }else {
            return notFound(action);
        }
    }

    //获取商品列表
    private Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
