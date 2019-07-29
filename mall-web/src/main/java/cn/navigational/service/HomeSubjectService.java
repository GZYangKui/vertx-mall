package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface HomeSubjectService {
    /**
     * 获取首页推荐专题列表
     */
    Future<JsonObject> list(JsonObject obj);
}
