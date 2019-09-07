package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

@Verticle()
@Router(api = "/api/user")
public class UserRouter extends RouterVerticle {
    private UserService service;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new UserServiceImpl(vertx,config());
    }

    @RequestMapping(api = "/login",method = HttpMethod.POST)
    public void login(final EBRequest request, final Promise<JsonObject> promise){
        promise.complete(responseSuccessJson());
    }
}
