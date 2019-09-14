package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import cn.navigational.model.AdminUser;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Vertx;
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
    private List skips;

    private RBACValidator(Vertx vertx, JsonObject config) {
        service = new UserServiceImpl(vertx, config);
        skips = config.getJsonArray(SKIP).getList();
    }

    @Override
    public void handle(RoutingContext event) {
        String uri = event.request().path();
        if (skips.contains(uri)) {
            event.next();
            return;
        }

        long userId = event.getBodyAsJson().getJsonObject(USER).getLong(USER_ID);

        service.getUserFromRedis(userId).setHandler(ar -> {
            if (ar.failed()) {
                logger.error("从redis中获取用户权限失败:{}", nullableStr(ar.cause()));
                validatorFailed(event, "鉴权失败");
                return;
            }

            JsonObject userInfo = ar.result();
            List permissions = userInfo.getJsonArray("permissions").getList();
            List roles = userInfo.getJsonArray("roles").getList();

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
