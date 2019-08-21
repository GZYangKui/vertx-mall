package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface HomeAdvertiseService {
    /**
     *
     * 获取商城首页广告位
     *
     * @param type 广告类型
     * @return
     */
    Future<List<JsonObject>> list(int type);
}
