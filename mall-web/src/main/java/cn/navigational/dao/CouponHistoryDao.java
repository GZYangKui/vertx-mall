package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface CouponHistoryDao {
    /**
     *  获取优惠券领取记录
     * @param userId 用户id
     * @param page  分页查询参数
     *
     */
    Future<List<JsonObject>> getList(long userId, Paging page);

    /**
     *
     * 获取领取记录对应的优惠券信息
     * @param ids 优惠券id列表
     *
     */
    Future<List<JsonObject>> getCoupon(List<Integer> ids);

    /**
     *
     * 删除领取记录
     *
     * @param ids 被删除记录id
     * @return
     */
    Future<List<Integer>> deleteHistory(JsonArray ids);

}
