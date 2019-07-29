package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.CartService;
import cn.navigational.service.impl.CartServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/cart", description = "购物车")
public class CartRouter extends RouterVerticle {
    private CartService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new CartServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取购物车商品列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    @RequestMapping(api = "/computer", method = HttpMethod.POST, description = "计算购物车内商品价格")
    public Future<JsonObject> computer(JsonObject obj) {
        return service.computer(obj);
    }

    @RequestMapping(api = "/updateNum", method = HttpMethod.POST, description = "更新购物车商品数量")
    public Future<JsonObject> updateNum(JsonObject obj){
        return service.updateNum(obj);
    }

    @RequestMapping(api = "/deletes", method = HttpMethod.POST, description = "删除购物车中的商品")
    public Future<JsonObject> deletes(JsonObject obj){
        return service.deletes(obj);
    }
}
