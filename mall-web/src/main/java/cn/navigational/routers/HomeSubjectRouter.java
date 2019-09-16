package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.HomeSubjectService;
import cn.navigational.service.impl.HomeSubjectServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle
@Router(api = "/api/homeSubject", description = "首页推荐专题")
public class HomeSubjectRouter extends RouterVerticle {

    private HomeSubjectService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new HomeSubjectServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/list", method = HttpMethod.GET, description = "获取首页专题推荐列表")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.list(), promise);
    }

}
