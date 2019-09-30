package cn.navigational.utils;

import io.vertx.core.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

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
    private static final Logger logger = LogManager.getLogger(RedisUtils.class);
    private static RedisUtils utils;
    private Redis redis;

    private RedisUtils(Vertx vertx, JsonObject config) {
        JsonObject redisClient = config.getJsonObject(REDIS_CLIENT);
        /**************************************************
         *             初始化Redis连接信息                   *
         **************************************************/
        String host = redisClient.getString(HOST);
        int port = redisClient.getInteger(PORT);
        int maxSize = redisClient.getInteger("maxSize");
        int maxWait = redisClient.getInteger("maxWait");
        String endpoint = "redis://" + host + ":" + port;
        System.out.println(endpoint);
        logger.info("==========开始初始化Redis===========");

        RedisOptions options = new RedisOptions();
        options.setEndpoint(endpoint);
        options.setMaxPoolSize(maxSize);
        options.setMaxPoolWaiting(maxWait);

        redis = Redis.createClient(vertx, options);

        logger.info("==========初始化成功===========");
    }

    /**
     * 向redis中写入数据
     *
     * @param key   数据key
     * @param value 数据值
     */
    public Future<Void> put(String key, String value) {
        Promise<Void> promise = Promise.promise();

        getRedisConnect().compose(r -> put(Arrays.asList(key, value), r)).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                return;
            }
            promise.complete();
        });

        return promise.future();
    }

    /**
     * 向redis写入数据
     *
     * @param array 键值对，例如:Arrays.asList("key1", "value1")
     * @param con   redis连接对象
     * @return 异步返回操作结果
     */
    private Future<Void> put(List<String> array, RedisConnection con) {
        Promise<Void> promise = Promise.promise();
        RedisAPI api = RedisAPI.api(con);
        api.set(array, ar -> {
            if (ar.failed()) {
                logger.error("Error writing data to redis:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
            con.close();
        });
        return promise.future();
    }


    public Future<Void> putEx(String key, String value, String expire) {
        Promise<Void> promise = Promise.promise();
        getRedisConnect().compose(r -> putEx(key, value, expire, r)).setHandler(ar -> {
            if (ar.failed()) {
                promise.fail(ar.cause());
                return;
            }
            promise.complete();
        });
        return promise.future();
    }

    /**
     * 向redis中写入数据并指定option(例如超时,过期等)
     *
     * @param key    键值对 key
     * @param value  键值对 value
     * @param expire 超时时间(单位为秒)
     * @param con    Redis连接实例对象
     */
    private Future<Void> putEx(String key, String value, String expire, RedisConnection con) {
        Promise<Void> promise = Promise.promise();
        RedisAPI api = RedisAPI.api(con);
        api.setex(key, expire, value, ar -> {
            if (ar.failed()) {
                logger.error("Error writing data to redis(EX):{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
            con.close();
        });
        return promise.future();
    }

    /**
     * 从redis中获取数据
     *
     * @param key 需要获取数据的key
     * @param h   异步回调接口
     */
    public void getJsonObject(String key, Handler<AsyncResult<JsonObject>> h) {
        getRedisConnect().compose(ar -> this.<String>getJson(key, ar)).setHandler(ar -> {
            if (ar.failed()) {
                h.handle(Future.failedFuture(ar.cause()));
            } else {
                h.handle(Future.succeededFuture(ar.result()));
            }
        });
    }

    private Future<JsonObject> getJson(String key, RedisConnection con) {
        Promise<JsonObject> promise = Promise.promise();
        RedisAPI api = RedisAPI.api(con);
        api.get(key, ar -> {
            if (ar.failed()) {
                logger.error("Get JsonObject from redis failed because:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            } else {
                JsonObject obj = new JsonObject();
                if (ar.result() != null) {
                    Response response = ar.result();
                    obj.mergeIn(response.toBuffer().toJsonObject());
                }
                promise.complete(obj);
            }
            con.close();
        });
        return promise.future();
    }

    private Future<Void> remove(List<String> keys, RedisConnection con) {
        Promise<Void> promise = Promise.promise();
        RedisAPI api = RedisAPI.api(con);

        api.del(keys, ar -> {
            if (ar.failed()) {
                logger.error("Delete data from redis failed because:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
            con.close();
        });
        return promise.future();
    }

    /**
     * 从redis中移除数据
     *
     * @param key     被移除数据key
     * @param handler 回调函数
     */
    public void remove(String key, Handler<AsyncResult<Void>> handler) {
        getRedisConnect().compose(r -> remove(Arrays.asList(key), r)).setHandler(ar -> {
            if (ar.failed()) {
                handler.handle(Future.failedFuture(ar.cause()));
            } else {
                handler.handle(Future.succeededFuture());
            }
        });

    }

    /**
     * 获取Redis连接对象
     *
     * @return {@link Future<RedisConnection>}
     */
    private Future<RedisConnection> getRedisConnect() {
        Promise<RedisConnection> promise = Promise.promise();
        redis.connect().setHandler(ar -> {
            if (ar.failed()) {
                logger.error("Get redis connect from connect pool because:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
                return;
            }
            promise.complete(ar.result());
        });
        return promise.future();
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
