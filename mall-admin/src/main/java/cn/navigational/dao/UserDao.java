package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.LoginLogger;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserDao extends BaseDao {
    public UserDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<Optional<JsonObject>> findAdminByUsername(String username) {
        String sql = "SELECT id,username,password,icon,email,nick_name AS \"nickName\"," +
                "create_time AS \"createTime\",status FROM mall_admin WHERE username=$1";
        return findAny(sql, Tuple.of(username));
    }

    public Future<Integer> saveLoginLogging(LoginLogger logger) {
        String sql = "INSERT INTO mall_admin_login_log(admin_id,create_time,ip,user_agent) VALUES($1,$2,$3,$4)";
        Tuple tuple = Tuple.of(logger.getAdminId(), LocalDateTime.now(), logger.getIp(), logger.getUserAgent());
        return executeUpdate(sql, tuple);
    }
}
