package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface HomeSubjectService {
    /**
     * 获取首页推荐专题列表
     */
    Future<List<JsonObject>> list();
}
