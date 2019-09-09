package cn.navigational.routers;

import cn.navigational.annotation.RequestMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.AdminUser;
import cn.navigational.model.EBRequest;
import cn.navigational.model.LoginLogger;
import cn.navigational.service.UserService;
import cn.navigational.service.impl.UserServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static cn.navigational.utils.Assert.isEmpty;
import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;


@Verticle
@Router(api = "/api/user")
public class UserRouter extends RouterVerticle {
    private UserService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new UserServiceImpl(vertx, config());
    }

    @RequestMapping(api = "/login", method = HttpMethod.POST)
    public void login(final EBRequest request, final Promise<JsonObject> promise) {
        JsonObject reqJson = request.getBodyAsJson();
        String username = reqJson.getString("username");
        String password = reqJson.getString("password");
        if (isEmpty(username)) {
            promise.complete(responseFailed("用户名不能为空", 200));
            return;
        }
        if (isEmpty(password)) {
            promise.complete(responseFailed("密码不能为空", 200));
            return;
        }
        service.findAdminUser(username).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                return;
            }
            AdminUser adminUser = ar.result();
            if (Objects.isNull(adminUser)) {
                promise.complete(responseFailed("账号不存在", 200));
                return;
            }
            if (adminUser.getStatus() == 0) {
                promise.complete(responseFailed("账号状态异常", 200));
                return;
            }
            boolean flag;
            try {
                flag = service.checkPassword(password, adminUser.getPassword());
            } catch (NoSuchAlgorithmException e) {
                logger.error("用户密码加密过程发生错误:{}", nullableStr(e.getCause()));
                e.printStackTrace();
                promise.fail(e);
                return;
            }
            if (!flag) {
                promise.complete(responseFailed("密码不正确", 200));
                return;
            }

            JsonObject info = JsonObject.mapFrom(adminUser);
            info.remove("password");
            ///生成jwt令牌///
            info.put("token", service.getUserToken(adminUser));
            ///回复请求信息///
            promise.complete(responseSuccessJson(info));

            ///保存管理员登录信息///
            LoginLogger logger = new LoginLogger();
            logger.setAdminId(adminUser.getId());
            logger.setUserAgent(request.getHeader("User-Agent"));
            logger.setIp(request.getHeader("ip"));

            service.recordAdminLogging(logger);
        });
    }
}
