package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.SubjectService;
import cn.navigational.service.impl.SubjectServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/subject", description = "商城专题")
public class SubjectRouter extends RouterVerticle {
    private SubjectService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new SubjectServiceImpl(vertx, config());
    }


    @RequestMapping(api = "/detail", description = "获取某一个专题详情", method = HttpMethod.GET)
    public Future<JsonObject> detail(JsonObject obj) {
        return service.detail(obj);
    }

    @RequestMapping(api = "/list", description = "获取商城专题列表", method = HttpMethod.GET)
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }
}
