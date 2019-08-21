package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.CouponService;
import cn.navigational.service.impl.CouponServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle
@Router(api = "/api/coupon", description = "商城优惠券")
public class CouponRoute extends RouterVerticle {
    private CouponService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new CouponServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", method = HttpMethod.GET, description = "优惠券列表")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        futureStatus(service.list(request.getPaging()),promise);
    }

}
