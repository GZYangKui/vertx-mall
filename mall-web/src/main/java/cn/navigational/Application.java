package cn.navigational;

import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.*;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Application {
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final DeploymentOptions options = new DeploymentOptions();
        options.setConfig(config);
        vertx.deployVerticle(new ApiVerticle(), options);
        vertx.deployVerticle(new UserRouter(), options);
        vertx.deployVerticle(new ProductRouter(), options);
        vertx.deployVerticle(new ProductCateRouter(), options);
        vertx.deployVerticle(new AddressRouter(), options);
        vertx.deployVerticle(new ProductSkuRouter(), options);
    }
}
