package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public interface SecKillService {
    /**
     * 获取首页最近时间段秒杀情况
     */
    Future<JsonObject> home(JsonObject obj);

    /**
     *
     * 获取抢购时间段
     * @return 返回的数据中包含一个{status}字段 该字段一共有三个值
     * 1 表示当前时间段正在抢购中
     * 2 表示当前时间段已开抢
     * 3 表示该时间段即将开抢
     *
     */
    Future<JsonObject> timeSlots(JsonObject obj);

    /**
     *
     * 获取每个时间段下的商品信息
     *
     *
     */
    Future<JsonObject> timeSlotWithProduct(JsonObject obj);

}
