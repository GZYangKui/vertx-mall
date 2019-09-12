package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.Optional;

public class AddressDao extends BaseDao {
    public AddressDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> getAddressList(long userId, Paging page) {
        final String sql = "SELECT * FROM receive_address WHERE member_id=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(userId, page.getPageSize(), page.getInitOffset()));
    }


    public Future<Optional<JsonObject>> getDefaultAddress(long userId) {
        final String sql = "SELECT * FROM receive_address WHERE member_id=$1 AND default_status = $2";
        return findAny(sql, Tuple.of(userId, 1));
    }


    public Future<Optional<JsonObject>> getDetail(int addressId) {
        final String sql = "SELECT * FROM receive_address WHERE id = $1";
        return findAny(sql, Tuple.of(addressId));
    }


    public Future<Integer> updateAddress(JsonObject address) {
        final String sql = "UPDATE  receive_address SET name=$1,phone=$2,default_status=$3,province=$4,city=$5,region=$6,detail_address=$7 WHERE id =$8";
        final Tuple tuple = Tuple.of(
                address.getString("name"),
                address.getString("phone"),
                address.getInteger("isDefault"),
                address.getString("province"),
                address.getString("city"),
                address.getString("region"),
                address.getString("detailAddress"),
                address.getInteger("id"));
        return executeUpdate(sql, tuple);
    }


    public Future<Integer> updateDefaultAddress(long userId, int addressId) {
        final String sql = "UPDATE receive_address SET default_status=$1 WHERE id!=$2 AND member_id=$3";
        return executeUpdate(sql, Tuple.of(0, addressId, userId));
    }


    public Future<Integer> create(JsonObject address, long userId) {
        final String sql = "INSERT INTO receive_address(name,phone,default_status,province,city,region,detail_address,member_id) VALUES ($1,$2,$3,$4,$5,$6,$7,$8)";

        final Tuple tuple = Tuple.of(
                address.getString("name"),
                address.getString("phone"),
                address.getInteger("isDefault"),
                address.getString("province"),
                address.getString("city"),
                address.getString("region"),
                address.getString("detailAddress"),
                userId);

        return insertSingle(sql, tuple);
    }


    public Future<Integer> setDefault(long userId, int addressId) {
        final String sql = "UPDATE receive_address SET default_status=$1 WHERE id=$2 AND member_id=$3";
        return executeUpdate(sql, Tuple.of(1, addressId, userId));
    }


    public Future<Integer> delete(long userId, long addressId) {
        final String sql = "DELETE FROM receive_address WHERE id=$1 AND member_id=$2";
        return executeUpdate(sql, Tuple.of(addressId, userId));
    }
}
