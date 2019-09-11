package cn.navigational.service;

import cn.navigational.model.AdminUser;
import cn.navigational.model.LoginLogger;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

public interface UserService {
    /**
     * 根据用户查找管理员
     *
     * @param username 管理员用户名
     * @return 异步管理员账号信息
     */
    Future<AdminUser> findAdminUser(String username);

    /**
     * 校验密码
     *
     * @param p1 用户上传上来的原始密码
     * @param p2 数据库存储的加密后的密码
     * @return 如果p1加密后和p2相等则返回true否则返回false
     */
    boolean checkPassword(String p1, String p2) throws NoSuchAlgorithmException;

    /**
     * 记录管理员登录日志
     *
     * @param logger 用户登录日志信息
     */
    void recordAdminLogging(LoginLogger logger);

    /**
     * 生成jwt令牌
     *
     * @param user 用户信息
     * @return 返回jwt令牌
     */
    String getUserToken(AdminUser user);

    /**
     * 得到用户权限并保存到redis
     *
     * @param adminId
     * @return
     */
    Future<List<String>> getUserPermissionAndSave(long adminId);

    /**
     *
     * 获取用户权限从redis
     * @param adminId 用户id
     */
    Future<List<String>> getUserFromRedis(long adminId);

}
