import base.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.ProductCateRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * 产品分类
 *
 */
public class ProductCateTest extends BaseTest{
    @BeforeClass
    public static void beforeClass(TestContext context) {
        final JsonObject config = vertx.fileSystem().readFileBlocking("config/config.json").toJsonObject();
        final DeploymentOptions deploymentOptions = new DeploymentOptions();

        deploymentOptions.setConfig(config);

        vertx.deployVerticle(new ApiVerticle(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), deploymentOptions, context.asyncAssertSuccess());
        vertx.deployVerticle(new ProductCateRouter(), deploymentOptions, context.asyncAssertSuccess());
    }

    //测试获取分类列表
    @Test
    public void testCategory(TestContext context){
        final Async async = context.async();
        webClient.get("/api/productCate/list").putHeader(String.valueOf(HttpHeaders.AUTHORIZATION),token).send(_rs->{
            if (_rs.failed()){
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            commonAssert(context,result);
            async.complete();
        });
    }
}
