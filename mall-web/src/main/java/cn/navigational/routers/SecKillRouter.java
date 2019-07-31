package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.SecKillService;
import cn.navigational.service.impl.SecKillServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/secKill", description = "商城秒杀")
public class SecKillRouter extends RouterVerticle {

    private SecKillService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new SecKillServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/home", description = "获取首页秒杀信息")
    public Future<JsonObject> list(JsonObject obj) {
        return service.home(obj);
    }

}
