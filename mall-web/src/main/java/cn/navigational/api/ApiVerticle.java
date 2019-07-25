package cn.navigational.api;

import cn.navigational.auth.RequireToken;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.HttpDataConverter;
import cn.navigational.impl.RestVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;


import static cn.navigational.config.Constants.PORT;
import static cn.navigational.utils.ResponseUtils.response;

@Verticle
public class ApiVerticle extends RestVerticle {
    public ApiVerticle() {
        super();
    }

    @Override
    public void start() throws Exception {
        super.start();
        final Router router = Router.router(vertx);

        router.route("/api/*").handler(CookieHandler.create());
        router.route("/api/*").handler(BodyHandler.create().setMergeFormAttributes(true));
        router.route("/api/*").handler(HttpDataConverter.create()).handler(RequireToken.create(vertx, config()));

        buildApi(router);

        router.get("/*").handler(StaticHandler.create().setDefaultContentEncoding("UTF-8"));

        final int port = config().getInteger(PORT, 8080);

        router.errorHandler(500, _routingContext -> {
            /*logger.error(_routingContext.failure().getCause().getMessage());*/
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
