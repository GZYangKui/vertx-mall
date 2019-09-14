package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface MemberLevelService {
    /**
     *
     * 获取商城会员等级
     *
     * @param defaultStatus 默认状态
     */
    Future<List<JsonObject>> list(int defaultStatus);
}
