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

    /**
     *
     *
     * 得到某个地址详情
     * @param addressId 地址id
     * @return 异步Optional
     */
    Future<Optional<JsonObject>> getDetail(int addressId);

    /**
     *
     *
     * 更新地址信息
     * @param address 地址信息
     *
     */
    Future<Integer> updateAddress(JsonObject address);


    /**
     *
     *
     * 更新默认地址
     * @param addressId 默认地址id
     *
     */
    Future<Integer> updateDefaultAddress(int addressId);

    /**
     *
     *
     * 创建收货地址
     *
     */
    Future<Integer> create(JsonObject address,long userId);


}
