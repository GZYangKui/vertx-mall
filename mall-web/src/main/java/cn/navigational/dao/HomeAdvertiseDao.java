package cn.navigational.dao;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface HomeAdvertiseDao {
    /**
     * 从数据库查询广告位列表
     *
     * @param type 广告类型 0->web商城 1->app商城
     * @return 返回异步商城广告位集合
     */
    Future<List<JsonObject>> getList(int type);
}
