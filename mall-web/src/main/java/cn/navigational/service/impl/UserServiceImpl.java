package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.UserDao;
import cn.navigational.dao.impl.UserDaoImpl;
import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import cn.navigational.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;


import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ResponseUtils.*;
import static cn.navigational.utils.StringUtils.getCurrentTime;
import static cn.navigational.utils.TokenUtils.generateKey;
import static cn.navigational.utils.TokenUtils.sha1;

public class UserServiceImpl extends BaseService implements UserService {
    private final UserDao dao;

    public UserServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
        dao = new UserDaoImpl(vertx, config);
    }

    @Override
    public Future<JsonObject> register(RegisterInfo info) {
        final Promise<JsonObject> promise = Promise.promise();
        final Promise<Optional<JsonObject>> optionalPromise = Promise.promise();
        final User temp = new User();
        temp.setUsername(info.getUsername());
        dao.getUser(temp).setHandler(optionalPromise.future());
        optionalPromise.future().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final Optional<JsonObject> optional = _rs.result();
            if (optional.isEmpty()) {
                promise.complete(responseFailed("用户名已存在", 200));
                return;
            }
            info.setCreateTime(getCurrentTime());
            try {
                info.setPassword(sha1(info.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                promise.fail("注册失败");
                return;
            }
            dao.register(info).setHandler(_rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                    return;
                }
                promise.complete(responseSuccessJson("注册成功"));
            });
        });
        return promise.future();
    }

    @Override
    public Future<JsonObject> login(User user) {
        final Promise<JsonObject> promise = Promise.promise();
        dao.getUser(user).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            if (_rs.result().isEmpty()) {
                promise.complete(responseFailed("用户不存在", 200));
                return;
            }
            final JsonObject temp = _rs.result().get();
            final String password = temp.getString(PASSWORD);
            try {
                user.setPassword(sha1(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error("加密用户密码失败:{}", e.getMessage());
                promise.fail("登录过程发生未知错误");
                return;
            }
            if (!password.equals(user.getPassword())) {
                promise.complete(responseFailed("密码不正确", 200));
                return;
            }
            final JsonObject claim = new JsonObject();
            claim.put(USER_ID, temp.getInteger(ID));
            claim.put(USERNAME, temp.getString(USERNAME));
            final String token = getJwtToken(claim);
            final JsonObject data = new JsonObject();
            data.put(TOKEN, token);
            promise.complete(responseSuccessJson(data));
        });
        return promise.future();
    }

    private String getJwtToken(JsonObject claim) {
        final JsonObject jwtConfig = config.getJsonObject("jwtConfig");
        final Key key = generateKey(jwtConfig.getString("key"));
        final Long term = jwtConfig.getLong("term");
        final JwtBuilder jwt = Jwts.builder()
                .setIssuer(jwtConfig.getString("issuer"))
                //设置令牌有效期
                .setExpiration(new Date(System.currentTimeMillis() + term * 60 * 1000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, key);
        jwt.addClaims(claim.getMap());
        return jwt.compact();
    }
}
