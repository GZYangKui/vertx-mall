package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.CartService;
import cn.navigational.service.impl.CartServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static cn.navigational.config.Constants.BODY;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

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
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        service.list(request.getUser().getUserId(), request.getPaging()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson(_rs.result()));
        });
    }

    @RequestMapping(api = "/computer", method = HttpMethod.POST, description = "计算购物车内商品价格")
    public void computer(final EBRequest request, final Promise<JsonObject> promise) {
        final List<JsonObject> list = request.getBodyAsJsonArray().getList();
        if (list.isEmpty()) {
            promise.complete(responseFailed("商品数量不能为空", 200));
            return;
        }
        service.getProducts(list).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(new JsonObject().put("totalAmount", service.computer(_rs.result())));
        });
    }

    @RequestMapping(api = "/updateNum", method = HttpMethod.POST, description = "更新购物车商品数量")
    public void updateNum(final EBRequest request, final Promise<JsonObject> promise) {
        final long number = request.getBodyAsJson().getLong("number");
        final long cartId = Long.parseLong(request.getQuery("cartId"));
        service.updateNum(number, cartId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
    }

    @RequestMapping(api = "/deletes", method = HttpMethod.POST, description = "删除购物车中的商品")
    public void deletes(final EBRequest request, final Promise<JsonObject> promise) {
        final List<Integer> cartIds = request.getBodyAsJsonArray().getList();
       service.deletes(cartIds).setHandler(_rs->{
          if (_rs.failed()){
              promise.fail(_rs.cause());
              return;
          }
          promise.complete(responseSuccessJson());
       });
    }
}
