package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.StringUtils.isContainChinese;
import static cn.navigational.utils.StringUtils.isEmpty;

/**
 * 检测用户信息(username/password)
 */
public class UserValidator extends HttpValidator {

    @Override
    public void handle(RoutingContext routingContext) {
        final JsonObject body = routingContext.getBody().toJsonObject().getJsonObject(BODY);
        if (body == null || body.isEmpty()) {
            validatorFailed(routingContext, "未检测到用户名和密码");
            return;
        }
        final String username = body.getString(USERNAME);
        final String password = body.getString(PASSWORD);
        if (isEmpty(username) || isEmpty(password)) {
            validatorFailed(routingContext, "用户名/密码不能为空!");
            return;
        }
        if (isContainChinese(username) || isContainChinese(password)) {
            validatorFailed(routingContext, "用户名和密码不能包括中文字符");
            return;
        }
        if (password.length() < 6) {
            validatorFailed(routingContext, "密码长度不能小于6位");
            return;
        }
        routingContext.next();
    }

    public static HttpValidator create() {
        return new UserValidator();
    }
}
