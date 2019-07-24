package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    /**
     *
     * 获取地址列表
     *
     * @param userId 用户id
     * @param page 分页参数
     *
     */
    Future<List<JsonObject>> getAddressList(long userId, Paging page);

    /**
     *
     * 获取默认收货地址
     *
     */
    Future<Optional<JsonObject>> getDefaultAddress(long userId);


}
