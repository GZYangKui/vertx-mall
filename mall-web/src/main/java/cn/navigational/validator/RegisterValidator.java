package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.*;
import static cn.navigational.utils.Assert.isEmpty;
import static cn.navigational.utils.StringUtils.checkMobileNumber;


public class RegisterValidator extends UserValidator {
    @Override
    public void handle(RoutingContext routingContext) {
        final JsonObject body = routingContext.getBodyAsJson().getJsonObject(BODY);
        final String phone = body.getString(PHONE);
        final Integer gender = body.getInteger(GENDER);
        if (!isEmpty(phone)) {
            boolean flag = checkMobileNumber(phone);
            if (!flag) {
                validatorFailed(routingContext, "请正确填写手机号码");
                return;
            }
        }
        if (gender < 0 || gender > 1) {
            validatorFailed(routingContext, "参数错误");
            return;
        }
        super.handle(routingContext);
    }

    public static HttpValidator create() {
        return new RegisterValidator();
    }
}
