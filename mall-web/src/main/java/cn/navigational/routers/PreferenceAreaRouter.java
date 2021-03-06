package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.PreferenceService;
import cn.navigational.service.impl.PreferenceServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle
@Router(api = "/api/preference", description = "优选专区")
public class PreferenceAreaRouter extends RouterVerticle {
    private PreferenceService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new PreferenceServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/list", description = "获取优选专题列表")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.list(request.getPaging()),promise);
    }
}
