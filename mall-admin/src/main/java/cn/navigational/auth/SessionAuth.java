package cn.navigational.auth;

import cn.navigational.base.HttpValidator;
import cn.navigational.model.AdminUser;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Objects;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.ResponseUtils.response;
import static cn.navigational.utils.ResponseUtils.responseFailed;

/**
 * 通过查询redis中的缓存检查用户是否超时(回话超时)
 *
 * @author YangKui
 * @since 1.0
 */
public class SessionAuth extends HttpValidator {
    private final UserService userService;
    private final JsonObject config;

    private SessionAuth(Vertx vertx, JsonObject config) {
        userService = new UserServiceImpl(vertx, config);
        this.config = config;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        if (isSkip(config, routingContext)) {
            return;
        }
        JsonObject user = routingContext.getBodyAsJson().getJsonObject(USER);
        long userId = user.getLong(USER_ID);
        userService.getUserFromRedis(userId).setHandler(ar -> {
            if (ar.failed() || ar.result().isEmpty()) {
                //要求用户登录
                response(routingContext, responseFailed("请登录", 403));
                //注销用户信息
                userService.logout(userId);
                return;
            }
            //跳转到下一个路由处理
            routingContext.next();
            AdminUser adminUser = ar.result().getJsonObject("user").mapTo(AdminUser.class);
            //刷新令牌和用户权限
            userService.getUserPermissionAndSave(adminUser);
        });
    }

    public static HttpValidator create(Vertx vertx, JsonObject config) {
        return new SessionAuth(vertx, config);
    }

}
