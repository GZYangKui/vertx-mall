package cn.navigational.service;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;


public interface CouponHistoryService {
    /**
     * 获取优惠券领取记录
     *
     * @param userId 用户id
     * @param paging 分页查询参数
     * @return 优惠券列表
     */
    Future<List<JsonObject>> list(long userId, Paging paging);

    /**
     * 删除领取记录
     *
     * @param ids 优惠券id列表
     * @return 返回影响数据行数
     */
    Future<Integer> deleteHistory(JsonArray ids);
}
