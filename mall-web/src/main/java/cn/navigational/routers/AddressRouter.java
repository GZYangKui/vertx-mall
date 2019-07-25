package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.AddressService;
import cn.navigational.service.impl.AddressServiceImpl;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/address")
public class AddressRouter extends RouterVerticle {
    private AddressService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new AddressServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取地址列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    @RequestMapping(api = "/default", method = HttpMethod.GET, description = "获取默认收货地址")
    public Future<JsonObject> defaultAddress(JsonObject obj) {
        return service.getDefaultAddress(obj);
    }
}
