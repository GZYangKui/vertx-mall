package cn.navigational.service;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface SubjectService {
    /**
     * 获取商城专题
     *
     * @param obj
     * @return
     */
    Future<JsonObject> list(JsonObject obj);

    /**
     *
     *
     * 获取专题详情
     *
     */
    Future<JsonObject> detail(JsonObject obj);
}
