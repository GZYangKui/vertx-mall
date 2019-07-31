package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface SecKillService {
    /**
     * 获取首页最近时间段秒杀情况
     */
    Future<JsonObject> home(JsonObject obj);

}
