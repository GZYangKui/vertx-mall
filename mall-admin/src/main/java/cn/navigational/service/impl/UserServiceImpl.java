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
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.RedisOptions;
import io.vertx.redis.op.SetOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
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
    public static final String REDIS_USER_PREFIX = "redis-user-";

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
    public Future<List<String>> getUserPermissionAndSave(long adminId) {
        Promise<List<String>> promise = Promise.promise();
        dao.getUserPermission(adminId).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("获取用户权限失败:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                ar.cause().printStackTrace();
                return;
            }
            //获取权限值
            List<String> list = ar.result().stream()
                    .map(r -> r.getString("value"))
                    .collect(Collectors.toList());

            savePermissionToRedis(adminId, list);

            //返回数据
            promise.complete(list);
        });
        return promise.future();
    }

    @Override
    public Future<List<String>> getUserFromRedis(long adminId) {
        Promise<List<String>> promise = Promise.promise();
        String key = REDIS_USER_PREFIX + adminId;
        redis.get(key, ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                return;
            }
            //将字符串转换为字符串集合
            String str = ar.result();
            if (Objects.nonNull(str)) {
                promise.complete(strToList(str));
            }
        });
        return promise.future();
    }

    //将用户权限缓存进redis
    private void savePermissionToRedis(long adminId, List<String> permissions) {
        //生成rediskey
        String key = REDIS_USER_PREFIX + adminId;
        //设置过期时间
        SetOptions options = new SetOptions();

        //设置两小时过期
        options.setEX(2 * 60 * 60);

        //写进redis
        redis.put(key, permissions.toString(), options);
    }
}
