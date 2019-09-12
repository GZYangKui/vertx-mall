package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.RegisterInfo;
import cn.navigational.model.User;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.Optional;

public class UserDao extends BaseDao{

    public UserDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<Integer> register(RegisterInfo info) {
        final String sql = "INSERT INTO member(username,password,gender,create_time) VALUES($1,$2,$3,$4)";
        final Tuple tuple = Tuple.of(info.getUsername(), info.getPassword(), info.getGender(), info.getCreateTime());
        return executeUpdate(sql, tuple);
    }


    public Future<Optional<JsonObject>> getUser(User user) {
        final String sql = "SELECT * FROM member WHERE username=$1";
        return findAny(sql, Tuple.of(user.getUsername()));
    }
}
