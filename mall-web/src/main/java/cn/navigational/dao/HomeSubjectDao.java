package cn.navigational.dao;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface HomeSubjectDao {
    /**
     * 从数据库查找首页推荐列表
     *
     * @return 异步json集合, 此数据是通过sort字段进行降序排序
     */
    Future<List<JsonObject>> getList();

    /**
     * 获取专题信息
     *
     * @param ids 专题id列表
     * @return 异步返回专题列表
     */
    Future<List<JsonObject>> getSubjectList(List<Integer> ids);
}
