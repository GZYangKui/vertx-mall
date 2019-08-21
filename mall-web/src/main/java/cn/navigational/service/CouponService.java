package cn.navigational.service;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;


public interface CouponService {
    /**
     *
     * 获取优惠券列表
     * @param paging 分页查询参数
     *
     */
    Future<List<JsonObject>> list(Paging paging);
}
