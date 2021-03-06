import base.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.PreferenceAreaRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 优选专题
 */
public class PreferenceTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(TestContext context) {
        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final DeploymentOptions deploymentOptions = new DeploymentOptions();

        deploymentOptions.setConfig(config);

        vertx.deployVerticle(new ApiVerticle(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new PreferenceAreaRouter(), deploymentOptions, context.asyncAssertSuccess());
    }

    @Test
    public void testPreference(TestContext context) {
        final Async async = context.async();
        webClient.get("/api/preference/list?page=1&pageSize=10").putHeader(HttpHeaders.AUTHORIZATION.toString(), token).send(_rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            commonAssert(context, result);
            async.complete();
        });
    }

}
