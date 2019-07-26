package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
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
    public Future<JsonObject> login(JsonObject obj) {
        final User user = obj.getJsonObject(BODY).mapTo(User.class);
        return service.login(user);
    }

    @RequestMapping(api = "/register", description = "用户注册", method = HttpMethod.POST)
    public Future<JsonObject> register(JsonObject obj) {
        final RegisterInfo registerInfo = obj.getJsonObject(BODY).mapTo(RegisterInfo.class);
        return service.register(registerInfo);
    }

}
