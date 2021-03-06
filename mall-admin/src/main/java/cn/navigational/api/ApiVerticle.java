package cn.navigational.api;

import cn.navigational.annotation.Verticle;
import cn.navigational.auth.RequireToken;
import cn.navigational.auth.SessionAuth;
import cn.navigational.impl.HttpDataConverter;
import cn.navigational.impl.RestVerticle;
import cn.navigational.validator.RBACValidator;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import static cn.navigational.config.Constants.PORT;
import static cn.navigational.utils.ExceptionUtils.nullableStr;

@Verticle
public class ApiVerticle extends RestVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        //错误处理程序
        exHandler(router);

        //开启自动处理http请求body
        router.route("/api/*").handler(BodyHandler.create().setMergeFormAttributes(true));
        //将http请求数据转换为json
        router.route("/api/*").handler(HttpDataConverter.create());
        //要求授权
        router.route("/api/*").handler(RequireToken.create(vertx, config()));
        //检查会话是否超时
        router.route("/api/*").handler(SessionAuth.create(vertx, config()));
        //检查用户权限
        router.route("/api/*").handler(RBACValidator.create(vertx, config()));

        //通过EventBus将请求转发到子路由上面处理
        router.route("/api/*").handler(this::sendMessage);

        int port = config().getInteger(PORT);

        //创建http服务器
        vertx.createHttpServer().requestHandler(router).listen(port, ar -> {
            if (ar.failed()) {
                startPromise.fail(ar.cause());
                logger.error("mall-admin server startup failded cause:{}", nullableStr(ar.cause()));
                return;
            }
            logger.info("mall-admin server success startup in port {}", port);
            startPromise.complete();
        });

    }
}
