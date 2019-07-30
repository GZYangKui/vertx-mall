package cn.navigational.dao;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductCateDao {
    /**
     * 获取商品分类列表
     *
     * @return 异步返回分类列表
     */
    Future<List<JsonObject>> getCateList();

}
