package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Future;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;


import static cn.navigational.config.Constants.BODY;

@Verticle()
@Router(api = "/api/user")
public class UserRouter extends RouterVerticle {

    private UserService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new UserServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/login", description = "用户登录", method = HttpMethod.POST)
    public void login(final EBRequest request, final Promise<JsonObject> promise) {
        final User user = request.getBodyAsJson().mapTo(User.class);
        service.login(user).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
    }

    @RequestMapping(api = "/register", description = "用户注册", method = HttpMethod.POST)
    public void register(final EBRequest request, final Promise<JsonObject> promise) {
        final RegisterInfo registerInfo = request.getBodyAsJson().mapTo(RegisterInfo.class);
        service.register(registerInfo).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
    }

}
