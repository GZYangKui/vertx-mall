package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDao extends BaseDao{
    public CartDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public Future<List<JsonObject>> getList(long userId, Paging paging) {
        final String sql = "SELECT * FROM cart_item WHERE member_id=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(userId, paging.getPageSize(), paging.getInitOffset()));
    }


    public Future<List<JsonObject>> getItems(List<Integer> ids) {
        final StringBuilder sb = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sb.append("SELECT * FROM cart_item WHERE id IN(");
        pingIn(sb, tuple, ids,1);
        return executeQuery(sb.toString(), tuple);
    }


    public Future<Optional<JsonObject>> item(long cartId) {
        final String sql = "SELECT * FROM cart_item WHERE id=$1";
        return findAny(sql, Tuple.of(cartId));
    }


    public Future<Integer> updateCartNum(long version, long quantity, long cartId) {
        final String sql = "UPDATE cart_item SET quantity=$1,version=$2 WHERE id=$3 AND version=$4";
        return executeUpdate(sql, Tuple.of(quantity, version + 1, cartId, version));
    }


    public Future<Integer> deletes(List<Integer> ids) {
        final String sql = "DELETE FROM cart_item WHERE id = $1";
        final List<Tuple> tuples = new ArrayList<>();
        ids.forEach(_r -> tuples.add(Tuple.of(_r)));
        return batchUpdate(sql, tuples);
    }
}
