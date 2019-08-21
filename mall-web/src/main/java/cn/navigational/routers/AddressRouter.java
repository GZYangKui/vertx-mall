package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import cn.navigational.service.AddressService;
import cn.navigational.service.impl.AddressServiceImpl;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;


import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle
@Router(api = "/api/address")
public class AddressRouter extends RouterVerticle {
    private AddressService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new AddressServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取地址列表")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        final JwtUser user = request.getUser();
        final Paging paging = request.getPaging();
        futureStatus(service.list(paging, user),promise);
    }

    @RequestMapping(api = "/default", method = HttpMethod.GET, description = "获取默认收货地址")
    public void defaultAddress(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.defaultAddress(request.getUser()),promise);
    }

    @RequestMapping(api = "/detail", method = HttpMethod.GET, description = "获取地址详情")
    public void detail(final EBRequest request, final Promise<JsonObject> promise) {
        service.detail(Integer.parseInt(request.getQuery("address_id"))).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().isEmpty() ? responseFailed("未找到对应的地址信息", 200):responseSuccessJson(_rs.result()));
        });
    }

    @RequestMapping(api = "/update", method = HttpMethod.POST, description = "更新地址信息")
    public void update(final EBRequest request, final Promise<JsonObject> promise) {
        final JsonObject info = request.getBodyAsJson();
        final long userId = request.getUser().getUserId();
        final int addressId = Integer.parseInt(request.getQuery("address_id"));
        service.update(info, userId, addressId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result() ? responseSuccessJson() : responseFailed("更新地址信息失败", 200));
        });
    }

    @RequestMapping(api = "/create", method = HttpMethod.POST, description = "添加新地址信息")
    public void create(final EBRequest request, final Promise<JsonObject> promise) {
        service.create(request.getBodyAsJson(), request.getUser().getUserId()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(responseSuccessJson());
        });
    }

    @RequestMapping(api = "/updateDefault", method = HttpMethod.POST, description = "更新默认地址")
    public void updateDefault(final EBRequest request, final Promise<JsonObject> promise) {
        final int addressId = request.getBodyAsJson().getInteger("addressId");
        final long userId = request.getUser().getUserId();
        service.updateDefault(addressId, userId).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result() ? responseSuccessJson() : responseFailed("地址信息不存在。", 200));
        });
    }

    @RequestMapping(api = "/delete", method = HttpMethod.POST, description = "删除地址信息")
    public void delete(final EBRequest request, final Promise<JsonObject> promise) {
        final int addressId = request.getBodyAsJson().getInteger("addressId");
        final long userId = request.getUser().getUserId();
        service.delete(addressId,userId).setHandler(_rs->{
            if (_rs.failed()){
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result()?responseSuccessJson():responseFailed("删除失败",200));
        });
    }
}
