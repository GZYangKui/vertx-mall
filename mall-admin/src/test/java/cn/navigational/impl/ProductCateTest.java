package cn.navigational.impl;

import cn.navigational.BaseTest;
import cn.navigational.api.ApiVerticle;
import cn.navigational.routers.ProductCateRouter;
import cn.navigational.routers.ProductRouter;
import cn.navigational.routers.UserRouter;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import org.junit.BeforeClass;
import org.junit.Test;

import static cn.navigational.config.Constants.DATA;
import static cn.navigational.config.Constants.TOKEN;

/**
 *
 * 分类列表测试
 *
 * @author YangKUi
 * @since 1.0
 */
public class ProductCateTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(TestContext context) {
        DeploymentOptions options = readConfig();
        vertx.deployVerticle(new ApiVerticle(), options, context.asyncAssertSuccess());
        vertx.deployVerticle(new UserRouter(), options, context.asyncAssertSuccess());
        vertx.deployVerticle(new ProductCateRouter(), options, context.asyncAssertSuccess());
    }

    @Test
    public void testCateList(TestContext context){
        Async async = context.async();
        String url = "/api/productCategory/list";
        JsonObject info = new JsonObject();
        info.put("pageIndex", 1);
        info.put("pageSize", 10);
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.post(port, host, url);
        request.headers().add("Authorization", "Bearer " + user.getJsonObject(DATA).getString(TOKEN));
        request.sendJson(info,ar -> {
            if (ar.failed()) {
                context.fail(ar.cause());
                return;
            }
            commonAsset(ar, context);
            async.complete();
        });
    }

    @Test
    public void testCateWithChildren(TestContext context){
        Async async = context.async();
        String url = "/api/productCategory/list/withChildren";
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.get(port, host, url);
        request.headers().add("Authorization", "Bearer " + user.getJsonObject(DATA).getString(TOKEN));
        request.send(ar -> {
            if (ar.failed()) {
                context.fail(ar.cause());
                return;
            }
            commonAsset(ar, context);
            async.complete();
        });
    }
}
