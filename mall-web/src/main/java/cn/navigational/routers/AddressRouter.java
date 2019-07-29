package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.AddressService;
import cn.navigational.service.impl.AddressServiceImpl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

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
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    @RequestMapping(api = "/default", method = HttpMethod.GET, description = "获取默认收货地址")
    public Future<JsonObject> defaultAddress(JsonObject obj) {
        return service.defaultAddress(obj);
    }

    @RequestMapping(api = "/detail", method = HttpMethod.GET, description = "获取地址详情")
    public Future<JsonObject> detail(JsonObject obj) {
        return service.detail(obj);
    }

    @RequestMapping(api = "/update", method = HttpMethod.POST, description = "更新地址信息")
    public Future<JsonObject> update(JsonObject obj) {
        return service.update(obj);
    }

    @RequestMapping(api = "/create", method = HttpMethod.POST, description = "添加新地址信息")
    public Future<JsonObject> create(JsonObject obj) {
        return service.create(obj);
    }

    @RequestMapping(api = "/updateDefault",method = HttpMethod.POST,description = "更新默认地址")
    public Future<JsonObject> updateDefault(JsonObject obj){
        return service.updateDefault(obj);
    }

    @RequestMapping(api = "/delete",method = HttpMethod.POST,description = "删除地址信息")
    public Future<JsonObject> delete(JsonObject obj){
        return service.delete(obj);
    }
}
