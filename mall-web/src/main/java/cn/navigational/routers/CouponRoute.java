package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.CouponService;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api ="/api/coupon")
public class CouponRoute extends RouterVerticle {
    private CouponService service;

    @Override
    public void start() throws Exception {
        super.start();
    }

    @Override
    protected Future<JsonObject> dispatch(String action, JsonObject data) {
        final Future<JsonObject> future;
        if (action.equals("/list")) {
            future = list(data);
        } else {
            future = notFound(action);
        }
        return future;
    }

    /**
     * 获取优惠券列表
     */
    private Future<JsonObject> list(JsonObject obj) {
        return service.list(obj);
    }

}
