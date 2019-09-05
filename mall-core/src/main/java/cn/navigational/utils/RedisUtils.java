package cn.navigational.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

/**
 * Redis 综合操作工具类(单例模式)
 *
 * @author YangKui
 * @see io.vertx.redis.client.Redis
 * @see RedisOptions
 * @since 1.0
 */
public class RedisUtils {
    private static final Logger LOGGER = LogManager.getLogger(RedisUtils.class);
    private static RedisUtils utils;
    private RedisAPI redisAPI;

    private RedisUtils(Vertx vertx, JsonObject config) {
        RedisOptions options = new RedisOptions();
        Redis redis = Redis.createClient(vertx, options);
        redis.connect(ar -> {
            if (ar.failed()) {
                LOGGER.error("创建RedisClient客户端失败:{}",
                        nullableStr(ar.cause()));
                return;
            }
            LOGGER.info("create redis-client success!");
            redisAPI = RedisAPI.api(redis);
        });
    }

    /**
     * 向redis中写入数据
     *
     * @param request 即将被存储进redis的数据
     * @param handler 回调函数
     */
    public void put(List<String> request, Handler<AsyncResult<Response>> handler) {
        redisAPI.set(request, ar -> {
            if (ar.failed()) {
                LOGGER.error("Redis send message failed cause:{}", nullableStr(ar.cause()));
                handler.handle(Future.failedFuture(ar.cause()));
                return;
            }
            handler.handle(Future.succeededFuture(ar.result()));
        });
    }

    /**
     * 从redis中获取数据
     *
     * @param arg0 key
     * @param h    异步回调接口
     */
    public void get(String arg0, Handler<AsyncResult<Response>> h) {
        redisAPI.get(arg0, ar -> {
            if (ar.failed()) {
                LOGGER.error("Get data from redis failde cause:{}", nullableStr(ar.cause()));
                h.handle(Future.failedFuture(ar.cause()));
                return;
            }
            h.handle(Future.succeededFuture(ar.result()));
        });
    }

    public static RedisUtils create(Vertx vertx, JsonObject config) {
        if (utils == null) {
            utils = new RedisUtils(vertx, config);
        }
        return utils;
    }
}
