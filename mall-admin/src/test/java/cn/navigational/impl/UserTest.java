package cn.navigational.impl;

import cn.navigational.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.web.client.WebClient;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(TestContext context) {
        DeploymentOptions options = readConfig();
        vertx.deployVerticle(new ApiVerticle(), options, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), options, context.asyncAssertSuccess());
    }

    @Test
    public void testLogin(TestContext context) {
        Async async = context.async();
        WebClient client = WebClient.create(vertx);
        JsonObject info = new JsonObject();
        client.post(port, host, "/api/user/login").sendJson(info, ar -> {
            if (ar.failed()) {
                context.fail(ar.cause());
                return;
            }
            JsonObject obj = ar.result().bodyAsJsonObject();
            System.out.println(obj.encodePrettily());
            async.complete();
        });
    }
}
