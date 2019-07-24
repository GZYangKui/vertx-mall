package cn.navigational;

import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.ProductSkuRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
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
public class ProductSkuTest {
    private static WebClient webClient;
    private static final String host = "127.0.0.1";
    private static final int port = 8080;
    private final Logger logger = LogManager.getLogger();
    private static Vertx vertx = Vertx.vertx();
    private  String token = "";

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

    @BeforeClass
    public static void  before(TestContext context) {

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
        vertx.deployVerticle(new ProductSkuRouter(),deployOptions,context.asyncAssertSuccess());
    }


    @Test
    public void testProductSku(TestContext context) {
        Async async = context.async();
        HttpRequest<Buffer> request = webClient.get("/api/productSku/sku?productId=10");
        request.putHeader(HttpHeaders.AUTHORIZATION.toString(),token);
        request.send(_rs -> {
            if (_rs.failed()){
                context.fail(_rs.cause());
            }
            System.out.println(_rs.result().bodyAsJsonObject());
            async.complete();
        });
    }


    @After
    public void after(TestContext context) {
        webClient.close();
        vertx.close(context.asyncAssertSuccess());
    }
}
