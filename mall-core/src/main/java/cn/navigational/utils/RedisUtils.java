package cn.navigational.utils;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private Redis client;
    private RedisAPI redis;

    private RedisUtils(Vertx vertx, JsonObject config) {
        JsonObject redisConfig = config.getJsonObject(REDIS_CLIENT);
        /**************************************************
         *             初始化Redis连接信息                   *
         **************************************************/
        String host = redisConfig.getString(HOST);
        int port = redisConfig.getInteger(PORT);
        int maxSize = redisConfig.getInteger("maxSize");
        int maxWait = redisConfig.getInteger("maxWait");
        String endpoint = "redis://" + host + ":" + port;
        logger.info("==========开始初始化Redis===========");

        RedisOptions options = new RedisOptions();
        options.setEndpoint(endpoint);
        options.setMaxPoolSize(maxSize);
        options.setMaxPoolWaiting(maxWait);
        try {
            client = Redis.createClient(vertx, options);
        } catch (Exception e) {
            logger.error("初始化Redis失败:{}", nullableStr(e));
            return;
        }
        client.connect(ar -> {
            if (ar.failed()) {
                logger.error("获取redis连接失败:{}", nullableStr(ar.cause()));
                return;
            }
            redis = RedisAPI.api(ar.result());
            logger.info("==========初始化成功===========");
        });

    }

//    /**
//     * 向redis中写入数据
//     *
//     * @param key   数据key
//     * @param value 数据值
//     */
//    public Future<Void> put(String key, String value) {
//        Promise<Void> promise = Promise.promise();
//
//        getRedisConnect().compose(r -> put(Arrays.asList(key, value), r)).setHandler(ar -> {
//            if (ar.failed()) {
//                promise.fail(ar.cause());
//                return;
//            }
//            promise.complete();
//        });
//
//        return promise.future();
//    }

    /**
     * 向redis写入数据
     *
     * @param key    数据key值
     * @param values 数据value值
     * @return 异步返回操作结果
     */
    private Future<Void> put(String key, String values) {
        Promise<Void> promise = Promise.promise();
        redis.set(List.of(key, values), ar -> {
            if (ar.failed()) {
                logger.error("Error writing data to redis:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
        });
        return promise.future();
    }


    /**
     * 向redis中写入数据并指定option(例如超时,过期等)
     *
     * @param key    键值对 key
     * @param value  键值对 value
     * @param expire 超时时间(单位为秒)
     */
    public Future<Void> putEx(String key, String value, String expire) {
        Promise<Void> promise = Promise.promise();
        redis.setex(key, expire, value, ar -> {
            if (ar.failed()) {
                logger.error("Error writing data to redis(EX):{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
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
        getValue(key).setHandler(ar -> {
            if (ar.failed()) {
                h.handle(Future.failedFuture(ar.cause()));
                return;
            }
            JsonObject obj = new JsonObject();
            if (ar.result() != null) {
                obj.mergeIn(ar.result().toBuffer().toJsonObject());
            }
            h.handle(Future.succeededFuture(obj));
        });
    }

    private Future<Response> getValue(String key) {
        Promise<Response> promise = Promise.promise();
        redis.get(key, ar -> {
            if (ar.failed()) {
                logger.error("Get value from redis failed because:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete(ar.result());
            }
        });
        return promise.future();
    }

    public Future<Void> remove(List<String> keys) {
        Promise<Void> promise = Promise.promise();
        redis.del(keys, ar -> {
            if (ar.failed()) {
                logger.error("Delete data from redis failed because:{}", nullableStr(ar.cause()));
                promise.fail(ar.cause());
            } else {
                promise.complete();
            }
        });
        return promise.future();
    }
//
//    /**
//     * 从redis中移除数据
//     *
//     * @param key     被移除数据key
//     * @param handler 回调函数
//     */
//    public void remove(String key, Handler<AsyncResult<Void>> handler) {
//        getRedisConnect().compose(r -> remove(Collections.singletonList(key), r)).setHandler(ar -> {
//            if (ar.failed()) {
//                handler.handle(Future.failedFuture(ar.cause()));
//            } else {
//                handler.handle(Future.succeededFuture());
//            }
//        });
//
//    }

//    /**
//     * 获取Redis连接对象
//     *
//     * @return {@link Future<RedisConnection>}
//     */
//    private Future<RedisConnection> getRedisConnect() {
//        Promise<RedisConnection> promise = Promise.promise();
//        redis.connect().setHandler(ar -> {
//            if (ar.failed()) {
//                logger.error("Get redis connect from connect pool because:{}", nullableStr(ar.cause()));
//                promise.fail(ar.cause());
//                return;
//            }
//            promise.complete(ar.result());
//        });
//        return promise.future();
//    }

    /**
     * 通过加锁方式创建单例
     *
     * @param vertx vertx实例对象
     * @param config 配置信息
     * @return 返回#RedisUtils 实例
     */
    public synchronized static RedisUtils create(Vertx vertx, JsonObject config) {
        if (utils == null) {
            utils = new RedisUtils(vertx, config);
        }
        return utils;
    }
}
