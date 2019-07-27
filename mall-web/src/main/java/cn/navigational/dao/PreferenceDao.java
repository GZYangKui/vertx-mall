package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface PreferenceDao {
    /**
     * 获取优选专区列表
     *
     * @param page 分页查询参数
     * @return 异步返回json集合
     */
    Future<List<JsonObject>> getList(Paging page);

    /**
     * 获取优选关联商品id
     *
     * @param preferenceIds 优选专题id
     * @return 异步返回产品id集合
     */
    Future<List<JsonObject>> getRelateProductId(List<Integer> preferenceIds);
}
