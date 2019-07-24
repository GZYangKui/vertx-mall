package cn.navigational.api;

import cn.navigational.auth.RequireToken;
import cn.navigational.impl.HttpDataConverter;
import cn.navigational.impl.RestVerticle;
import cn.navigational.utils.ResponseUtils;
import cn.navigational.validator.RegisterValidator;
import cn.navigational.validator.UserValidator;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;

import java.util.ArrayList;
import java.util.List;

import static cn.navigational.config.Constants.PORT;
import static cn.navigational.config.Constants.SKIP;
import static cn.navigational.utils.ResponseUtils.response;

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

        router.post("/api/user/login").handler(UserValidator.create()).handler(this::sendMessage);

        router.post("/api/user/register").handler(UserValidator.create()).handler(RegisterValidator.create()).handler(this::sendMessage);

        router.get("/api/product/list");

        router.get("/api/productCate/list");

        router.get("/api/productSku/sku");

        router.get("/api/address/list");

        router.get("/api/address/default");

        router.route("/api/*").handler(this::sendMessage);

        final int port = config().getInteger(PORT, 8080);


        router.errorHandler(500, _routingContext -> {
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
