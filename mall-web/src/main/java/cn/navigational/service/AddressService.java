package cn.navigational.service;

import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface AddressService {
    /**
     * 获取收货地址列表
     *
     * @param paging 分页参数
     * @param user   用户信息
     * @return
     */
    Future<List<JsonObject>> list(Paging paging, JwtUser user);

    /**
     * 获取默认收货地址
     */
    Future<JsonObject> defaultAddress(JwtUser user);


    /**
     * 获取地址详情
     */
    Future<JsonObject> detail(int addressId);

    /**
     * 更新地址信息
     */
    Future<Boolean> update(JsonObject info, long userId, int addressId);

    /**
     * 新建收货地址
     *
     * @param info   地址信息
     * @param userId 用户id
     * @return 如果创建成功返回true
     */
    Future<Boolean> create(JsonObject info, long userId);

    /**
     * 更新默认地址信息
     *
     * @return 如果更新成功返回true 否则false
     */
    Future<Boolean> updateDefault(int addressId, long userId);

    /**
     * 删除地址信息
     *
     * @return 如果更新成功返回true 否则返回false
     */
    Future<Boolean> delete(int addressId, long userId);

}
