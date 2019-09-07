package cn.navigational.service.impl;

import cn.navigational.dao.UserDao;
import cn.navigational.service.UserService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    public UserServiceImpl(Vertx vertx, JsonObject config) {
        dao = new UserDao(vertx, config);
    }
}
