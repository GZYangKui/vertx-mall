package cn.navigational.service;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface PreferenceService {
    /**
     * 获取优选专区列表
     */
    Future<List<JsonObject>> list(Paging paging);
}
