package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.CouponHistoryService;
import cn.navigational.service.impl.CouponHistoryServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/couponHis", description = "优惠券领取记录")
public class CouponHistoryRouter extends RouterVerticle {
    private CouponHistoryService service;

    @Override
    public void start() throws Exception {
        super.start();
        service = new CouponHistoryServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list",method = HttpMethod.GET,description = "优惠券领取记录")
    public Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    @RequestMapping(api = "/delete",method = HttpMethod.POST,description = "删除某张领取记录")
    public Future<JsonObject> delete(JsonObject obj) {
        return service.deleteHistory(obj);
    }
}
