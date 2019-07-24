package cn.navigational.routers;

import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Future;

import io.vertx.core.json.JsonObject;


import static cn.navigational.config.Constants.BODY;

@Verticle(description = "用户信息")
public class UserRouter extends RouterVerticle {

    private UserService service;

    @Override
    public void start() throws Exception {
        super.start("/api/user");
        service = new UserServiceImpl(vertx, config());
    }

    @Override
    public Future<JsonObject> dispatch(String action, JsonObject data) {
        if (action.equals("/login")) {
            return login(data);
        } else if (action.equals("/register")) {
            return register(data);
        } else {
            return notFound(action);
        }
    }

    //用户登录
    private Future<JsonObject> login(JsonObject obj) {
        final User user = obj.getJsonObject(BODY).mapTo(User.class);
        return service.login(user);
    }

    //用户注册
    private Future<JsonObject> register(JsonObject obj) {
        final RegisterInfo registerInfo = obj.getJsonObject(BODY).mapTo(RegisterInfo.class);
        return service.register(registerInfo);
    }

}
