package cn.navigational.service.impl;

import cn.navigational.dao.UserDao;
import cn.navigational.model.AdminUser;
import cn.navigational.model.LoginLogger;
import cn.navigational.service.UserService;
import cn.navigational.utils.ExceptionUtils;
import cn.navigational.utils.TokenUtils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    public Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(Vertx vertx, JsonObject config) {
        dao = new UserDao(vertx, config);
    }

    @Override
    public Future<AdminUser> findAdminUser(String username) {
        Promise<AdminUser> promise = Promise.promise();
        dao.findAdminByUsername(username).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("根据管理员用户名查询管理员账号信息失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            Optional<JsonObject> optional = ar.result();
            if (optional.isEmpty()) {
                promise.complete(null);
            } else {
                AdminUser adminUser = optional.get().mapTo(AdminUser.class);
                promise.complete(adminUser);
            }
        });
        return promise.future();
    }

    @Override
    public boolean checkPassword(String p1, String p2) throws NoSuchAlgorithmException {
        String temp = TokenUtils.sha1(p1);
        return temp.equals(p2);
    }

    @Override
    public void recordAdminLogging(LoginLogger logger) {
        dao.saveLoginLogging(logger);
    }
}
