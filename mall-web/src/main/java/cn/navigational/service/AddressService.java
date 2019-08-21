package cn.navigational.service;

import cn.navigational.model.JwtUser;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface AddressService {
    /**
     *
     * 获取收货地址列表
     *
     * @param  paging 分页参数
     * @param user 用户信息
     *
     * @return
     */
    Future<List<JsonObject>> list(Paging paging, JwtUser user);

    /**
     *
     *
     * 获取默认收货地址
     *
     */
    Future<JsonObject> defaultAddress(JwtUser user);


    /**
     *
     * 获取地址详情
     *
     */
    Future<JsonObject> detail(JsonObject obj);

    /**
     *
     * 更新地址信息
     *
     *
     */
    Future<JsonObject> update(JsonObject obj);

    /**
     *
     *
     * 新建收货地址
     *
     */
    Future<JsonObject> create(JsonObject obj);

    /**
     *
     * 更新默认地址信息
     *
     *
     */
    Future<JsonObject> updateDefault(JsonObject obj);

    /**
     *
     *
     * 删除地址信息
     *
     */
    Future<JsonObject> delete(JsonObject obj);

}
