package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.AddressDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.Optional;

public class AddressDaoImpl extends BaseDao implements AddressDao {
    public AddressDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getAddressList(long userId, Paging page) {
        final String sql = "SELECT * FROM receive_address WHERE member_id=$1 LIMIT $2 OFFSET $3";
        return executeQuery(sql, Tuple.of(userId, page.getPageSize(), page.getInitOffset()));
    }

    @Override
    public Future<Optional<JsonObject>> getDefaultAddress(long userId) {
        final String sql = "SELECT * FROM receive_address WHERE member_id=$1 AND default_status = $2";
        return findAny(sql, Tuple.of(userId, 1));
    }
}
