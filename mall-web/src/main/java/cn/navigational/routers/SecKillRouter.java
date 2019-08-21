package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.SecKillService;
import cn.navigational.service.impl.SecKillServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
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
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.home(), promise);
    }

    @RequestMapping(api = "/timeSlots", method = HttpMethod.GET, description = "获取请购时间段")
    public void timeSlots(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.timeSlots(), promise);
    }

    @RequestMapping(api = "/timeSlotWithProduct", method = HttpMethod.GET, description = "获取某个时间段的商品信息")
    public void timeSlotWithProduct(final EBRequest request, final Promise<JsonObject> promise) {
        final long timeSlotId = Long.parseLong(request.getQuery("timeSlotId"));
        futureStatus(service.timeSlotWithProduct(timeSlotId), promise);
    }

}
