package cn.navigational;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class BaseTest {
    protected Vertx vertx = Vertx.vertx();
    protected String host = "127.0.0.1";
    protected int port = 8081;

    /**
     * 执行登录获取token
     *
     * @param context
     */
    @Before
   public void start(TestContext context) {
        context.async().complete();
    }

    @After
    public void stop(TestContext context) {
        Async async = context.async();
        vertx.close(ar -> {
            if (ar.failed()) {
                context.fail(ar.cause());
                return;
            }
            async.complete();
        });
    }
}
