package cn.navigational.impl;

import cn.navigational.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import org.junit.BeforeClass;
import org.junit.Test;

import static cn.navigational.config.Constants.DATA;
import static cn.navigational.config.Constants.TOKEN;

public class UserTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(TestContext context) {
        DeploymentOptions options = readConfig();
        vertx.deployVerticle(new ApiVerticle(), options, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), options, context.asyncAssertSuccess());
    }

    @Test
    public void testUserPermission(TestContext context) {
        Async async = context.async();
        String url = "/api/user/userPermission";
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.get(port, host, url);
        request.headers().add("Authorization", "Bearer "+user.getJsonObject(DATA).getString(TOKEN));
        request.send(ar -> {
            commonAsset(ar, context);
            async.complete();
        });
    }
}
