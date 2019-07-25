package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface CouponDao {
    /**
     * 获取优惠券列表
     *
     * @param page 分页参数
     * @return 异步结果
     */
    Future<List<JsonObject>> getList(Paging page);
}
