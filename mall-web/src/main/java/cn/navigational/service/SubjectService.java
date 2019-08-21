package cn.navigational.service;


import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface SubjectService {
    /**
     * 获取商城专题
     *
     * @param paging 分页查询参数
     * @param cateId 专题分类id
     *
     */
    Future<List<JsonObject>> list(Paging paging,int cateId);

    /**
     *
     *
     * 获取专题详情
     * @param subjectId 专题id
     *
     */
    Future<JsonObject> detail(int subjectId);

    /**
     *
     * 获取分类列表
     *
     *
     */
    Future<List<JsonObject>> cateList();
}
