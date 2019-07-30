package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface ProductCateService {
    /**
     *
     * 获取分类列表
     *
     *
     */
    Future<JsonObject> list(JsonObject obj);

}
