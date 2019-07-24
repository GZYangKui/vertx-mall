package cn.navigational;

import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static cn.navigational.config.Constants.*;

@RunWith(VertxUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAccountTest {
    private WebClient webClient;
    private final String host = "127.0.0.1";
    private final int port = 8080;
    private final Logger logger = LogManager.getLogger();
    private Vertx vertx = Vertx.vertx();

    @Before
    public void before(TestContext context) {
        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final DeploymentOptions deployOptions = new DeploymentOptions();
        final WebClientOptions options = new WebClientOptions();

        options.setSsl(false);
        options.setFollowRedirects(true);
        options.setDefaultHost(host);
        options.setDefaultPort(port);

        webClient = WebClient.create(vertx, options);

        deployOptions.setConfig(config);

        vertx.deployVerticle(new ApiVerticle(), deployOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), deployOptions, context.asyncAssertSuccess());
    }

    @Test
    public void testNotExistUserLogin(TestContext context) {
        final Async async = context.async();
        final JsonObject info = new JsonObject();
        info.put(USERNAME, "yangkui123");
        info.put(PASSWORD, "123456");
        webClient.post("/api/user/login").sendJson(info, _rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(!result.getBoolean(FLAG));
            logger.info(result.getString(MESSAGE));
            async.complete();
        });
    }

    @Test
    public void testUserLoginContainChinese(TestContext context) {
        final Async async = context.async();
        final JsonObject info = new JsonObject();
        info.put(USERNAME, "杨奎");
        info.put(PASSWORD, "123456");
        webClient.post("/api/user/login").sendJson(info,_rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
                return;
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            System.out.println(result.encodePrettily());
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(!result.getBoolean(FLAG));
            logger.info(result.getString(MESSAGE));
            async.complete();
        });
    }

    @Test
    public void testUserLoginMinPassword(TestContext context) {
        final Async async = context.async();
        final JsonObject info = new JsonObject();
        info.put(USERNAME, "yangkui");
        info.put(PASSWORD, "12345");
        webClient.post("/api/user/login").sendJson(info, _rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(!result.getBoolean(FLAG));
            logger.info(result.getString(MESSAGE));
            async.complete();
        });
    }

    @Test
    public void testNormLogin(TestContext context) {
        final Async async = context.async();
        final JsonObject info = new JsonObject();
        info.put(USERNAME, "yangkui");
        info.put(PASSWORD, "123456");
        webClient.post("/api/user/login").sendJson(info, _rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(result.getBoolean(FLAG));
            context.assertNotNull(result.getJsonObject(DATA));
            logger.info(result.getJsonObject(DATA).getString(TOKEN));
            async.complete();
        });
    }

    @Test
    public void testRegister(TestContext context) {
        final Async async = context.async();
        final JsonObject info = new JsonObject();
        info.put(USERNAME, "hahahahjjjsj");
        info.put(PASSWORD, "123456");
        info.put(GENDER, 0);

        webClient.post("/api/user/register").sendJson(info, _rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            System.out.println(result);
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(result.getBoolean(FLAG));
            async.complete();
        });
    }

    @After
    public void after(TestContext context) {
        webClient.close();
        vertx.close(context.asyncAssertSuccess());
    }

}