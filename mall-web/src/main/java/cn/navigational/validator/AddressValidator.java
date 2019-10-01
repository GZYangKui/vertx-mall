package cn.navigational.validator;

import cn.navigational.base.HttpValidator;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.BODY;

import static cn.navigational.utils.StringUtils.checkMobileNumber;
import static cn.navigational.utils.StringUtils.isEmpty;

/**
 * 校验用户地址信息
 * 用户地址信息必须包括姓名、电话、省、市、区(可选)、详细地址、是否默认地址选项。
 * @author YangKui
 * @since 1.0
 */
public class AddressValidator extends HttpValidator {
    @Override
    public void handle(RoutingContext routingContext) {
        final JsonObject info = routingContext.getBodyAsJson().getJsonObject(BODY);
        if (info.isEmpty()) {
            validatorFailed(routingContext, "地址信息不能为空");
            return;
        }

        final String name = info.getString("name");
        final String phone = info.getString("phone");
        final int isDefault = info.getInteger("isDefault");
        final String detailAddress = info.getString("detailAddress");
        final String province = info.getString("province");
        final String city = info.getString("city");

        if (isEmpty(name)) {
            validatorFailed(routingContext, "名字不能为空");
            return;
        }
        if (isEmpty(phone)) {
            validatorFailed(routingContext, "手机号不能为空");
            return;
        }
        if (!checkMobileNumber(phone)) {
            validatorFailed(routingContext, "手机号码格式错误。");
            return;
        }
        if (isDefault < 0 || isDefault > 1) {
            validatorFailed(routingContext, "默认地址参数错误");
            return;
        }
        if (isEmpty(detailAddress)) {
            validatorFailed(routingContext, "详细地址不能为空");
            return;
        }
        if (isEmpty(province)) {
            validatorFailed(routingContext, "省份不能为空");
            return;
        }
        if (isEmpty(city)) {
            validatorFailed(routingContext, "所在城市不能为空");
            return;
        }
        routingContext.next();
    }

    public static HttpValidator create() {
        return new AddressValidator();
    }
}
