package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.AddressService;
import cn.navigational.service.impl.AddressServiceImpl;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api ="api/address" )
public class AddressRouter extends RouterVerticle {
    private AddressService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new AddressServiceImpl(vertx, config());
    }

    @Override
    protected Future<JsonObject> dispatch(String action, JsonObject data) {
        final Future<JsonObject> future;
        if (action.equals("/list")) {
            future = list(data);
        } else if (action.equals("/default")) {
            future = defaultAddress(data);
        } else {
            future = notFound(action);
        }
        return future;
    }

    private Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    private Future<JsonObject> defaultAddress(JsonObject obj) {
        return service.getDefaultAddress(obj);
    }
}
