package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import cn.navigational.model.AdminUser;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ExceptionUtils.nullableStr;

/**
 * 用户权限验证
 *
 * @author YangKui
 * @since 1.0
 */
public class RBACValidator extends HttpValidator {
    private UserService service;
    private JsonObject config;

    private RBACValidator(Vertx vertx, JsonObject config) {
        service = new UserServiceImpl(vertx, config);
        this.config = config;
    }

    @Override
    public void handle(RoutingContext event) {
        if (isSkip(config, event)) {
            return;
        }

        long userId = event.getBodyAsJson().getJsonObject(USER).getLong(USER_ID);

        service.getUserFromRedis(userId).setHandler(ar -> {
            if (ar.failed() || ar.result().isEmpty()) {
                logger.error("从redis中获取用户权限失败:{}", nullableStr(ar.cause()));
                validatorFailed(event, "鉴权失败(可能会话已过期)");
                return;
            }

            String uri = event.request().path();
            JsonObject userInfo = ar.result();
            JsonArray permissions = userInfo.getJsonArray("permissions");

            AdminUser user = userInfo.getJsonObject("user").mapTo(AdminUser.class);

            logger.info("用户:{} 申请访问资源:{}", user.getUsername(), uri);

            // '*'表示最高访问权
            if ((permissions.isEmpty() || !permissions.contains(uri)) && !permissions.contains("*")) {
                validatorFailed(event, "你没有访问改资源的权限");
                logger.info("的用户:{} 不具备访问:{} 资源的权限", user.getUsername(), uri);
                return;
            }

            logger.info("将资源:{} 授权给的用户:{}", uri, user.getNickName());
            event.next();
        });

    }

    public static HttpValidator create(Vertx vertx, JsonObject config) {
        return new RBACValidator(vertx, config);
    }
}
