package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 * 阿里云oss配置路由
 *
 * @author YangKUi
 * @since 1.0
 */
@Verticle
@Router(api = "/api/oss")
public class OSSRouter extends RouterVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @RouterMapping(api = "/policy",description = "签阿里云oss")
    public void policy(final EBRequest request, final Promise<JsonObject> response){

    }
}
