package cn.navigational.base;

import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

import static cn.navigational.config.Constants.QUERY;
import static cn.navigational.config.Constants.USER;

public class BaseService {
    protected Logger logger = LogManager.getLogger();
    protected final Vertx vertx;
    protected final JsonObject config;

    public BaseService(Vertx vertx, JsonObject config) {
        this.config = config;
        this.vertx = vertx;
    }

    /**
     * 获取Http请求头中的query
     */
    protected JsonObject getQuery(JsonObject obj) {
        return obj.getJsonObject(QUERY);
    }

    /**
     * 获取jwt令牌中的用户信息
     */
    protected JwtUser getUser(JsonObject obj) {
        return obj.getJsonObject(USER).mapTo(JwtUser.class);
    }

    /**
     * 获取query中制定参数的值
     */
    protected String getQuery(JsonObject object, String key) {
        return getQuery(object).getString(key);
    }

    /**
     * 获取分页参数
     */
    protected Paging getPaging(JsonObject obj) {
        return new Paging(getQuery(obj));
    }

}
