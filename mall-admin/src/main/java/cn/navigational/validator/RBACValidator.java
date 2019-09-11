package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import cn.navigational.dao.UserDao;
import cn.navigational.utils.RedisUtils;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.USER;
import static cn.navigational.config.Constants.USER_ID;
import static cn.navigational.utils.ExceptionUtils.nullableStr;

/**
 * 用户权限验证
 *
 * @author YangKui
 * @since 1.0
 */
public class RBACValidator extends HttpValidator {
    private UserDao dao;
    private RedisUtils redis;

    private RBACValidator(Vertx vertx, JsonObject config) {
        dao = new UserDao(vertx, config);
    }

    @Override
    public void handle(RoutingContext event) {
        JsonObject user = event.getBodyAsJson().getJsonObject(USER);
        long userId = user.getLong(USER_ID);
        //生成redis存储key
        String key = "redis-user-" + userId;
        redis.get(key, ar -> {
            if (ar.failed()) {
                validatorFailed(event, "授权过程发生错误");
                logger.error("从redis中获取用户权限列表失败:{}", nullableStr(ar.cause()));
                return;
            }
            //如果拥有此权限则放过
            event.next();
        });
    }

    public static HttpValidator create(Vertx vertx, JsonObject config) {
        return new RBACValidator(vertx, config);
    }
}
