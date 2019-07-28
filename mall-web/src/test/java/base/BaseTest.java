package base;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static cn.navigational.config.Constants.*;

@RunWith(VertxUnitRunner.class)
public class BaseTest {

    protected String token = "";
    protected  WebClient webClient;

    protected static Vertx vertx = Vertx.vertx();

    /**
     * 登录获取token
     *
     * @param context 异步测试上下文
     */
    @Before
    public void before(TestContext context) {
        final Async async = context.async();
        final JsonObject userInfo = new JsonObject();
        final WebClientOptions options = new WebClientOptions();

        options.setDefaultHost("127.0.0.1");
        options.setDefaultPort(8080);
        options.setSsl(false);
        options.setFollowRedirects(true);

        vertx = Vertx.vertx();
        webClient = WebClient.create(vertx, options);

        webClient = WebClient.create(vertx,options);
        userInfo.put(USERNAME, "yangkui");
        userInfo.put(PASSWORD, "123456");
        webClient.post("/api/user/login").sendJson(userInfo, _rs -> {
            if (_rs.failed()) {
                context.fail(_rs.cause());
            }
            final JsonObject result = _rs.result().bodyAsJsonObject();
            commonAssert(context,result);
            token = "Bearer " + result.getJsonObject(DATA).getString(TOKEN);
            async.complete();
        });
    }

    @Test
    public void test(TestContext context){
        System.out.println("=========test========");
        context.async().complete();
    }

    /**
     * 关闭vertx和webclient实例
     *
     * @param context
     */
    @After
    public void close(TestContext context) {
        webClient.close();
        vertx.close(context.asyncAssertSuccess());
    }

    protected void commonAssert(TestContext context,JsonObject result){
        System.out.println(result.encodePrettily());
        context.assertTrue(result.getInteger(CODE) == 200);
        context.assertTrue(result.getBoolean(FLAG));
    }
}
