package cn.navigational.service;

import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface UserService {
    //用户登录
    Future<JsonObject> login(User user);

    //用户注册
    Future<JsonObject> register(RegisterInfo info);

}
