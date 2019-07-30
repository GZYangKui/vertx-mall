package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.HomeSubjectService;
import cn.navigational.service.impl.HomeSubjectServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/homeSubject", description = "首页推荐专题")
public class HomeSubjectRouter extends RouterVerticle {

    private HomeSubjectService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new HomeSubjectServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "获取首页专题推荐列表")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

}
