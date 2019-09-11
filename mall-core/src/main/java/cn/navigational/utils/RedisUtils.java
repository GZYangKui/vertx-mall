package cn.navigational.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import io.vertx.redis.op.SetOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

import static cn.navigational.config.Constants.*;
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
    private RedisClient client;

    private RedisUtils(Vertx vertx, JsonObject config) {
        RedisOptions options = new RedisOptions();
        JsonObject redisClient = config.getJsonObject(REDIS_CLIENT);
        options.setHost(redisClient.getString(HOST));
        options.setPort(redisClient.getInteger(PORT));
        client = RedisClient.create(vertx, options);
    }

    /**
     * 向redis中写入数据
     *
     * @param key   数据key
     * @param value 数据值
     */
    public void put(String key, String value, Handler<AsyncResult<String>> handler) {
        client.set(key, value, ar -> {
            if (ar.failed()) {
                LOGGER.error("向redis写入数据发生错误(指定option):{}", nullableStr(ar.cause()));
                handler.handle(Future.failedFuture(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }
            handler.handle(Future.succeededFuture());
        });
    }

    public void put(String key, String value,SetOptions options) {
        putWithOption(key, value, options,ar -> {
            //Empty
        });
    }

    /**
     * 向redis中写入数据并指定option(例如超时,过期等)
     *
     * @param key     键值对 key
     * @param value   键值对 value
     * @param options 键值对选项
     * @param handler 回调函数
     */
    public void putWithOption(String key, String value, SetOptions options, Handler<AsyncResult<String>> handler) {
        client.setWithOptions(key, value, options, ar -> {
            if (ar.failed()) {
                LOGGER.error("向redis写入数据发生错误:{}", nullableStr(ar.cause()));
                handler.handle(Future.failedFuture(ar.cause()));
                ar.cause().printStackTrace();
                return;
            }
            handler.handle(Future.succeededFuture());
        });
    }

    /**
     * 从redis中获取数据
     *
     * @param key 需要获取数据的key
     * @param h   异步回调接口
     */
    public void get(String key, Handler<AsyncResult<String>> h) {
        client.get(key, ar -> {
            if (ar.failed()) {
                LOGGER.error("Get data from redis failde cause:{}", nullableStr(ar.cause()));
                h.handle(Future.failedFuture(ar.cause()));
                return;
            }
            h.handle(Future.succeededFuture(ar.result()));
        });
    }

    /**
     * 通过加锁方式创建单例
     *
     * @param vertx
     * @param config
     * @return 返回#RedisUtils 实例
     */
    public synchronized static RedisUtils create(Vertx vertx, JsonObject config) {
        if (utils == null) {
            utils = new RedisUtils(vertx, config);
        }
        return utils;
    }
}
