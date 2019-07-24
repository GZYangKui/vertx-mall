package cn.navigational.dao;

import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


import java.util.Optional;


public interface UserDao {
    Future<Optional<JsonObject>> getUser(User user);

    Future<Integer> register(RegisterInfo info);
}
