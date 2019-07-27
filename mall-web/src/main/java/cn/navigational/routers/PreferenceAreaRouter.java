package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.PreferenceService;
import cn.navigational.service.impl.PreferenceServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/preference", description = "优选专区")
public class PreferenceAreaRouter extends RouterVerticle {
    private PreferenceService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new PreferenceServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取优选专题列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
