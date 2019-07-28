import base.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.HomeAdvertiseRouter;
import cn.navigational.routers.PreferenceAreaRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * 获取商城首页广告位
 *
 */
public class HomeAdvertiseTest extends BaseTest {
    @BeforeClass
    public static void beforeClass(TestContext context) {
        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final DeploymentOptions deploymentOptions = new DeploymentOptions();

        deploymentOptions.setConfig(config);

        vertx.deployVerticle(new ApiVerticle(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new HomeAdvertiseRouter(), deploymentOptions, context.asyncAssertSuccess());
    }

    @Test
    public void testHomeAdvertiseList(TestContext context) {
        final Async async = context.async();
        webClient.get("/api/homeAdvertise/list?type=0").putHeader(HttpHeaders.AUTHORIZATION.toString(), token).send(_rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            commonAssert(context, result);
            async.complete();
        });
    }
}
