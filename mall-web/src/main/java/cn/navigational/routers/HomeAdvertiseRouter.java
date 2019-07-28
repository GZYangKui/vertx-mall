package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.HomeAdvertiseService;
import cn.navigational.service.impl.HomeAdvertiseServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/homeAdvertise")
public class HomeAdvertiseRouter extends RouterVerticle {
    private HomeAdvertiseService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new HomeAdvertiseServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取商城首页广告位")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
