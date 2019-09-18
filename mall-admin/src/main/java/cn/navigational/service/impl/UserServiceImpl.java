package cn.navigational.service.impl;

import cn.navigational.dao.UserDao;
import cn.navigational.model.AdminUser;
import cn.navigational.model.LoginLogger;
import cn.navigational.service.UserService;
import cn.navigational.utils.RedisUtils;
import cn.navigational.utils.TokenUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.op.SetOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.StringUtils.strToList;
import static cn.navigational.utils.TokenUtils.generateKey;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private JsonObject config;

    private RedisUtils redis;

    //redis缓存用户权限key前缀
    private static final String REDIS_USER_PREFIX = "redis-user-";

    public UserServiceImpl(Vertx vertx, JsonObject config) {
        this.config = config;
        dao = new UserDao(vertx, config);
        redis = RedisUtils.create(vertx, config);
    }

    @Override
    public Future<AdminUser> findAdminUser(String username) {
        Promise<AdminUser> promise = Promise.promise();
        dao.findAdminByUsername(username).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("根据管理员用户名查询管理员账号信息失败:{}", nullableStr(ar.cause()));
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
    public void recordAdminLogging(LoginLogger logger, AdminUser user) {
        dao.saveLoginLogging(logger).setHandler(ar -> {
            if (ar.failed()) {
                this.logger.error("保存用户登录日志失败:{}", nullableStr(ar.cause()));
                return;
            }
            this.logger.info("保存用户{}登录记录成功", user.getUsername());
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
    public Future<JsonObject> getUserPermissionAndSave(AdminUser user) {
        Promise<JsonObject> promise = Promise.promise();
        dao.getUserPermission(user.getId()).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取用户权限失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            //获取权限值
            List<String> permissions = ar.result().stream()
                    .map(r -> r.getString("value"))
                    .collect(Collectors.toList());
            List<String> roles = ar.result().stream()
                    .map(r -> r.getString("roleName"))
                    .collect(Collectors.toList());
            //生成缓存json数据
            JsonObject rp = new JsonObject();
            rp.put("roles", roles);
            rp.put("permissions", permissions);
            rp.put("user", JsonObject.mapFrom(user));

            flushToken(user.getId(), rp);
            //返回数据
            promise.complete(rp);
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> getUserFromRedis(long adminId) {
        Promise<JsonObject> promise = Promise.promise();
        String key = REDIS_USER_PREFIX + adminId;
        redis.get(key, ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                return;
            }
            String str = ar.result();
            promise.complete(Objects.isNull(str) ? new JsonObject() : new JsonObject(str));
        });
        return promise.future();
    }

    @Override
    public void logout(long adminId) {
        redis.remove(REDIS_USER_PREFIX + adminId, ar -> {
        });
    }

    @Override
    public Future<JsonObject> userInfo(long adminId) {
        Promise<JsonObject> promise = Promise.promise();
        dao.getUserInfo(adminId).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取用户信息失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            //如果信息不存在则返回空json
            promise.complete(ar.result().orElse(new JsonObject()));
        });
        return promise.future();
    }

    //将用户权限缓存进redis
    public void flushToken(long adminId, JsonObject userInfo) {
        //生成rediskey
        String key = REDIS_USER_PREFIX + adminId;
        //设置过期时间
        SetOptions options = new SetOptions();

        //设置15分钟之内没有活动 登出
        options.setEX(15*60);

        //写进redis
        redis.put(key, userInfo.toString(), options);
    }
}
