package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.Paging;
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
    public void detail(final EBRequest request, final Promise<JsonObject> promise) {
        final int subjectId = Integer.parseInt(request.getQuery("subjectId"));
        futureStatus(service.detail(subjectId),promise);
    }

    @RequestMapping(api = "/list", description = "获取商城专题列表", method = HttpMethod.GET)
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        final Paging paging = request.getPaging();
        final int cateId = Integer.parseInt(request.getQuery("cateId"));
        futureStatus(service.list(paging, cateId), promise);
    }

    @RequestMapping(api = "/cateList", method = HttpMethod.GET, description = "获取专题分类")
    public void cateList(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.cateList(), promise);
    }
}
