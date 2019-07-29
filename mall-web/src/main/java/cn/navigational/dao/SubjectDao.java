package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {
    /**
     * 获取商城专题列表
     *
     * @param page 分页参数
     * @param cateId 分类id
     *
     */
    Future<List<JsonObject>> getList(Paging page,int cateId);

    /**
     * 获取专题分类信息
     *
     * @param ids 分类id列表
     */
    Future<List<JsonObject>> getSubjectCateInfo(List<Integer> ids);

    /**
     *
     * 获取全部专题分类列表
     *
     */
    Future<List<JsonObject>> getSubjectCateList();

    /**
     * 获取某个专题详情
     *
     * @param subjectId 专题id
     */
    Future<Optional<JsonObject>> getSubjectInfo(int subjectId);
}
