package cn.navigational.service;

import cn.navigational.model.query.BrandQueryParam;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface BrandService {
    /**
     *
     * 获取品牌列表
     * @param param 请求查询参数
     * @return 异步返回json数据集合
     */
    Future<List<JsonObject>> list(BrandQueryParam param);

    /**
     *
     * 根据指定条件统计数量
     * @param param 条件
     * @return 异步返回符合条件的数据量
     */
    Future<Long> count(BrandQueryParam param);
}
