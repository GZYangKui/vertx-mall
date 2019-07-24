package cn.navigational;

import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.AddressRouter;
import cn.navigational.routers.ProductCateRouter;
import cn.navigational.routers.ProductRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static cn.navigational.config.Constants.*;

@RunWith(VertxUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressTest {
    private static WebClient webClient;
    private static final String host = "127.0.0.1";
    private static final int port = 8080;
    private final Logger logger = LogManager.getLogger();
    private static Vertx vertx = Vertx.vertx();
    private String token = "";

    @BeforeClass
    public static void before(TestContext context) {

        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final JsonArray api = vertx.fileSystem().readFileBlocking("config/api.json").toJsonArray();
        final DeploymentOptions deployOptions = new DeploymentOptions();
        final WebClientOptions options = new WebClientOptions();

        options.setSsl(false);
        options.setFollowRedirects(true);
        options.setDefaultHost(host);
        options.setDefaultPort(port);

        webClient = WebClient.create(vertx, options);

        config.put(API, api);
        deployOptions.setConfig(config);

        vertx.deployVerticle(new ApiVerticle(), deployOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), deployOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new AddressRouter(), deployOptions, context.asyncAssertSuccess());
    }
    @Before
    public void login(TestContext context) {
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
            token = "Bearer " + result.getJsonObject(DATA).getString(TOKEN);
            async.complete();
        });
    }


    @Test
    public void testAddressList(TestContext context) {
        Async async = context.async();
        HttpRequest<Buffer> request = webClient.get("/api/address/list?page=1&pageSize=10");
        request.putHeader(HttpHeaders.AUTHORIZATION.toString(), token);
        request.send(_rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(result.getBoolean(FLAG));
            System.out.println(result.encodePrettily());
            async.complete();
        });
    }

    @Test
    public void testDefaultAddress(TestContext context) {
        Async async = context.async();
        HttpRequest<Buffer> request = webClient.get("/api/address/default");
        request.putHeader(HttpHeaders.AUTHORIZATION.toString(), token);
        request.send(_rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            context.assertTrue(result.getInteger(CODE) == 200);
            context.assertTrue(result.getBoolean(FLAG));
            System.out.println(result.encodePrettily());
            async.complete();
        });
    }


    @AfterClass
    public static void after(TestContext context) {
        webClient.close();
        vertx.close(context.asyncAssertSuccess());
    }
}
