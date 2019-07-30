package cn.navigational.api;

import cn.navigational.auth.RequireToken;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.HttpDataConverter;
import cn.navigational.impl.RestVerticle;
import cn.navigational.validator.AddressValidator;
import cn.navigational.validator.RegisterValidator;
import cn.navigational.validator.UserValidator;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;


import static cn.navigational.config.Constants.PORT;
import static cn.navigational.utils.ResponseUtils.response;

@Verticle
public class ApiVerticle extends RestVerticle {

    @Override
    public void start() throws Exception {
        super.start();
        final Router router = Router.router(vertx);

        router.route("/api/*").handler(CookieHandler.create());

        router.route("/api/*").handler(BodyHandler.create().setMergeFormAttributes(true));

        router.route("/api/*").handler(HttpDataConverter.create()).handler(RequireToken.create(vertx, config()));

        //用户登录
        router.post("/api/user/login").handler(UserValidator.create());

        //用户注册
        router.post("/api/user/register").handler(RegisterValidator.create());

        //获取地址列表
        router.get("/api/address/list");

        //获取默认地址
        router.get("/api/address/default");

        //商品列表
        router.get("/api/product/list");

        //获取商品库存信息
        router.get("/api/sku/info");

        //获取商品分类列表
        router.get("/api/productCate/list");

        //获取优惠券列表
        router.get("/api/coupon/list");

        //获取优选专题列表
        router.get("/api/preference/list");

        //获取商城广告位
        router.get("/api/homeAdvertise/list");

        //获取地址详细信息
        router.get("/api/address/detail");

        //更新默认地址
        router.post("/api/address/updateDefault");

        //更新地址信息
        router.post("/api/address/update").handler(AddressValidator.create());

        //新增地址信息
        router.post("/api/address/create").handler(AddressValidator.create());

        //删除地址信息
        router.post("/api/address/delete");

        //获取专题分类
        router.get("/api/subject/cateList");

        //获取某个分类下的专题
        router.get("/api/subject/list");

        //获取购物车列表
        router.get("/api/cart/list");

        //更新购物车商品数量
        router.post("/api/cart/updateNum");

        //删除购物车商品数量
        router.post("/api/cart/deletes");

        //获取首页专题推荐列表
        router.get("/api/homeSubject/list");

        //获取今日推荐商品列表
        router.get("/api/product/toadyRecommend");


        //将请求分发到指定的分路由上去
        router.route("/api/*").handler(this::sendMessage);

        //匹配静态资源
        router.get("/*").handler(StaticHandler.create().setDefaultContentEncoding("UTF-8"));

        final int port = config().getInteger(PORT, 8080);

        router.errorHandler(500, _routingContext -> {
            _routingContext.failure().printStackTrace();
            response(_routingContext, executeException(_routingContext.failure()));
        });
        router.errorHandler(404, _routingContext -> {
            response(_routingContext, http404());
        });

        vertx.createHttpServer().requestHandler(router).listen(port, _rs -> {
            if (_rs.succeeded()) {
                logger.info("Server success listener in port {} ", port);
            } else {
                logger.error("Server startup failed cause:{}", _rs.cause().getMessage());
            }
        });
    }
}
