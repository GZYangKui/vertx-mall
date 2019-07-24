package cn.navigational.base;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

}
