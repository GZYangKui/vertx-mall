package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.MemberLevelService;
import cn.navigational.service.impl.MemberLevelServiceImpl;
import cn.navigational.utils.ResponseUtils;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

/**
 * 商城会员等级管理
 *
 * @author YangKUi
 * @since 1.0
 */
@Verticle
@Router(api = "/api/memberLevel")
public class MemberLevelRouter extends RouterVerticle {
    private MemberLevelService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new MemberLevelServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/list", description = "获取商城会员等级列表")
    public void list(final EBRequest request, final Promise<JsonObject> response) {
        int defaultStatus = Integer.parseInt(request.getQuery("defaultStatus"));
        service.list(defaultStatus).setHandler(ar -> {
            if (ar.failed()) {
                response.complete(responseFailed("获取商城会员等级失败", 200));
                return;
            }
            response.complete(responseSuccessJson(ar.result()));
        });
    }
}
