package cn.navigational.service.impl;

import cn.navigational.dao.UserDao;
import cn.navigational.model.AdminUser;
import cn.navigational.model.LoginLogger;
import cn.navigational.service.UserService;
import cn.navigational.utils.TokenUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.TokenUtils.generateKey;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private JsonObject config;

    public UserServiceImpl(Vertx vertx, JsonObject config) {
        this.config = config;
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
        dao.saveLoginLogging(logger).setHandler(ar -> {
            if (ar.failed()) {
                this.logger.error("保存用户登录日志失败:{}", nullableStr(ar.cause()));
                return;
            }
            this.logger.info("保存用户登录记录成功,用户id:{}", logger.getAdminId());
        });
    }

    @Override
    public String getUserToken(AdminUser user) {
        JsonObject claim = new JsonObject();
        claim.put("userId", user.getId());
        JsonObject jwtConfig = config.getJsonObject("jwtConfig");
        Key key = generateKey(jwtConfig.getString("key"));
        Long term = jwtConfig.getLong("term");
        JwtBuilder jwt = Jwts.builder()
                .setIssuer(jwtConfig.getString("issuer"))
                //设置令牌有效期
                .setExpiration(new Date(System.currentTimeMillis() + term * 60 * 1000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, key);
        jwt.addClaims(claim.getMap());
        return jwt.compact();
    }

    @Override
    public Future<JsonObject> getUserPermission(long adminId) {
        Promise<JsonObject> promise = Promise.promise();
        dao.getUserPermission(adminId).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取用户角色和权限失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                ar.cause().printStackTrace();
                return;
            }
            List roles = new ArrayList<>();
            List<JsonObject> permissions = new ArrayList<>();
            ar.result().forEach(item -> {
                roles.add((item.remove("roleName")));
                permissions.add(item);
            });
            JsonObject data = new JsonObject();
            data.put("roles", roles);
            data.put("permissions", permissions);
            promise.complete(data);
        });
        return promise.future();
    }
}
