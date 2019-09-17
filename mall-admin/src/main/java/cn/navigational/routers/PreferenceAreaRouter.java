package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.PreferenceAreaService;
import cn.navigational.service.impl.PreferenceAreaServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

/**
 * 商城优选
 *
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/prefrenceArea")
public class PreferenceAreaRouter extends RouterVerticle {

    private PreferenceAreaService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new PreferenceAreaServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/listAll", description = "获取所有优选信息")
    public void listAll(final EBRequest request, final Promise<JsonObject> response) {
        service.listAllPrefrenceArea().setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取优选信息出错", 200));
                return;
            }
            response.complete(responseSuccessJson(ar.result()));
        });
    }
}
