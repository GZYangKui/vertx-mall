package cn.navigational;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;

import static cn.navigational.config.Constants.CODE;
import static cn.navigational.config.Constants.FLAG;


@RunWith(VertxUnitRunner.class)
public class BaseTest {
    protected final static Vertx vertx = Vertx.vertx();
    protected String host = "localhost";
    protected int port = 8081;
    protected JsonObject user;
    private final static String globalPath ="/home/yangkui/.vert.x-mall/global_config.json";
    private final static String localPath = "config/config.json";


    /**
     * 执行登录获取token
     *
     * @param context
     */
    @Before
    public void start(TestContext context) {
        Async async = context.async();
        String url ="/api/user/login";
        JsonObject info = new JsonObject();
        info.put("username","admin");
        info.put("password","123456");
        WebClient webClient = WebClient.create(vertx);
        webClient.post(port,host,url).sendJson(info,ar->{
            user = commonAsset(ar,context);
            async.complete();
        });

    }

    protected static  DeploymentOptions readConfig() {
        DeploymentOptions options = new DeploymentOptions();
        FileSystem fs = vertx.fileSystem();
        JsonObject globalConfig = fs.readFileBlocking(globalPath).toJsonObject();
        JsonObject config = fs.readFileBlocking(localPath).toJsonObject();
        config.mergeIn(globalConfig);
        options.setConfig(config);
        return options;
    }

    protected JsonObject commonAsset(AsyncResult<HttpResponse<Buffer>> ar, TestContext context){
        if (ar.failed()){
            context.fail(ar.cause());
            return null;
        }
        System.out.println(ar.result().bodyAsString());
        JsonObject obj = ar.result().bodyAsJsonObject();
        context.assertTrue(obj.getInteger(CODE)==200);
        context.assertTrue(obj.getBoolean(FLAG));
        return obj;
    }

    @AfterClass
    public static void stop(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }
}
