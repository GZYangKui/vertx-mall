package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductCateService {
    /**
     *
     * 获取分类列表
     *
     *
     */
    Future<List<JsonObject>> list();

}
