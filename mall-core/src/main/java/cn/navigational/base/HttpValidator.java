package cn.navigational.base;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.config.Constants.*;

public abstract class HttpValidator implements Handler<RoutingContext> {
    protected Logger logger = LogManager.getLogger();

    /**
     * 数据检测失败
     */
    protected void validatorFailed(RoutingContext rcx, String msg) {
        final JsonObject temp = new JsonObject();
        temp.put(CODE, 200);
        temp.put(MESSAGE, msg);
        temp.put(FLAG, false);
        temp.put(TIME_STAMP, System.currentTimeMillis());
        rcx.response().end(temp.toBuffer());
    }

    /**
     * 是否跳过验证
     *
     * @param config 配置信息
     * @param rcx    路由上下文
     * @return 跳过{true}否则{false}
     */
    protected boolean isSkip(JsonObject config, RoutingContext rcx) {
        String uri = rcx.request().path();
        List skips = config.getJsonArray("skip").getList();
        boolean flag = false;
        if (skips.contains(uri)) {
            rcx.next();
            flag = true;
        }
        return flag;
    }

}
