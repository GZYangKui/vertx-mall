package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@Verticle()
@Router(api = "/api/user")
public class UserRouter extends RouterVerticle {

    private UserService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new UserServiceImpl(vertx, config());
    }

    @RouterMapping(api = "/login", description = "用户登录", method = HttpMethod.POST)
    public void login(final EBRequest request, final Promise<JsonObject> promise) {
        final User user = request.getBodyAsJson().mapTo(User.class);
        futureStatus(service.login(user), promise);
    }

    @RouterMapping(api = "/register", description = "用户注册", method = HttpMethod.POST)
    public void register(final EBRequest request, final Promise<JsonObject> promise) {
        final RegisterInfo registerInfo = request.getBodyAsJson().mapTo(RegisterInfo.class);
        futureStatus(service.register(registerInfo), promise);
    }

}
