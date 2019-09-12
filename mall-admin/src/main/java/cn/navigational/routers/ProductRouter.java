package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.ProductService;
import cn.navigational.service.impl.ProductServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 *
 * 商品管理路由
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/product")
public class ProductRouter extends RouterVerticle {
    private ProductService service;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new ProductServiceImpl(vertx,config());
    }

    @RequestMapping(api = "/list",description = "获取商品列表")
    public void list(final EBRequest request, final Promise<JsonObject> response){

    }
}
