package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface PreferenceService {
    /**
     * 获取优选专区列表
     */
    Future<JsonObject> list(JsonObject obj);
}
