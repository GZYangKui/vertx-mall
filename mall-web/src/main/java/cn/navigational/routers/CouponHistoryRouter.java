package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.CouponHistoryService;
import cn.navigational.service.impl.CouponHistoryServiceImpl;
import io.vertx.core.Future;
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

    @Override
    protected Future<JsonObject> dispatch(String action, JsonObject data) {
        final Future<JsonObject> future;
        if (action.equals("/list")) {
            future = list(data);
        } else if (action.equals("/delete")) {
            future = delete(data);
        } else {
            future = notFound(action);
        }
        return future;
    }

    private Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

    private Future<JsonObject> delete(JsonObject obj) {
        return service.deleteHistory(obj);
    }
}
