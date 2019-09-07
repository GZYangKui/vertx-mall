package cn.navigational.api;

import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RestVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

import static cn.navigational.config.Constants.PORT;
import static cn.navigational.utils.ExceptionUtils.nullableStr;

@Verticle
public class ApiVerticle extends RestVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);

        int port = config().getInteger(PORT);


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
