package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface SubjectService {
    /**
     * 获取所有专题
     * @return 异步返回专题集合
     */
    Future<List<JsonObject>> listAllSubject();
}
