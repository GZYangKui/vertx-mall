package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.Paging;
import cn.navigational.service.CouponHistoryService;
import cn.navigational.service.impl.CouponHistoryServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle
@Router(api = "/api/couponHis", description = "优惠券领取记录")
public class CouponHistoryRouter extends RouterVerticle {
    private CouponHistoryService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new CouponHistoryServiceImpl(vertx, config());

    }

    @RouterMapping(api = "/list", method = HttpMethod.GET, description = "优惠券领取记录")
    public void list(final EBRequest request, final Promise<JsonObject> promise) {
        final long userId = request.getUser().getUserId();
        final Paging paging = request.getPaging();
        futureStatus(service.list(userId, paging), promise);
    }

    @RouterMapping(api = "/delete", method = HttpMethod.POST, description = "删除某张领取记录")
    public void delete(final EBRequest request, final Promise<JsonObject> promise) {
        service.deleteHistory(request.getBodyAsJsonArray()).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result() > 0 ? responseSuccessJson() : responseFailed("删除失败", 200));
        });
    }
}
