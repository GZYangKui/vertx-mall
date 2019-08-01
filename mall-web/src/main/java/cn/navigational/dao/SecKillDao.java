package cn.navigational.dao;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface SecKillDao {
    /**
     * 从数据库获取秒杀时间段
     *
     * @return 异步返回时间段列表
     */
    Future<List<JsonObject>> getTimeSlot();

    /**
     * 获取某个时间段内的秒杀活动
     *
     * @param timeSlotId 时间段id
     * @return 异步返回时间段商品信息
     *
     */
    Future<List<JsonObject>> getTimeSlotForProduct(long timeSlotId);
}
