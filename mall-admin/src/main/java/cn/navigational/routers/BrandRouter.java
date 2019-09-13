package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

@Verticle
@Router(api = "/api/brand")
public class BrandRouter extends RouterVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @RequestMapping(api = "/list")
    public void list(final EBRequest request, final Promise<JsonObject> promise){

    }
}
