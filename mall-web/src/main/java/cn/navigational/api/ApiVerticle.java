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

@Verticle
public class ApiVerticle extends RestVerticle {

    @Override
    public void start() throws Exception {
        super.start();
        final Router router = Router.router(vertx);

        //请求异常处理
        exHandler(router);

        router.route("/api/*").handler(BodyHandler.create().setMergeFormAttributes(true));

        router.route("/api/*").handler(HttpDataConverter.create()).handler(RequireToken.create(vertx, config()));

        //用户登录
        router.post("/api/user/login").handler(UserValidator.create());

        //用户注册
        router.post("/api/user/register").handler(RegisterValidator.create());

        //更新地址信息
        router.post("/api/address/update").handler(AddressValidator.create());

        //新增地址信息
        router.post("/api/address/create").handler(AddressValidator.create());

        //将请求分发到指定的分路由上去
        router.route("/api/*").handler(this::sendMessage);

        //匹配静态资源
        router.get("/*").handler(StaticHandler.create().setDefaultContentEncoding("UTF-8"));

        final int port = config().getInteger(PORT, 8080);


        vertx.createHttpServer().requestHandler(router).listen(port, _rs -> {
            if (_rs.succeeded()) {
                logger.info("Server success listener in port {} ", port);
            } else {
                logger.error("Server startup failed cause:{}", _rs.cause().getMessage());
            }
        });
    }
}
