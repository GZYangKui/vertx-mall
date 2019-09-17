package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface PreferenceAreaService {
    /**
     * 获取所有优选
     * @return 异步返回优选集合
     */
    Future<List<JsonObject>> listAllPrefrenceArea();
}
