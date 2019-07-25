package cn.navigational.base;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cn.navigational.config.Constants.*;

public class BaseDao {

    protected final PgPool client;

    public BaseDao(Vertx vertx, JsonObject config) {
        final JsonObject dbConfig = config.getJsonObject(DATA_SOURCE);

        final PgConnectOptions options = new PgConnectOptions();

        options.setHost(dbConfig.getString(HOST));
        options.setUser(dbConfig.getString(USER));
        options.setPassword(dbConfig.getString(PASSWORD));
        options.setDatabase(dbConfig.getString(DATABASE));
        options.setPort(dbConfig.getInteger(PORT));

        final PoolOptions poolOptions = new PoolOptions().setMaxSize(10);

        client = PgPool.pool(vertx, options, poolOptions);
    }

    /**
     * 执行查询
     *
     * @param sql sql语句
     * @return
     */
    protected Future<List<JsonObject>> executeQuery(final String sql) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        getConnection().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final SqlConnection con = _rs.result();
            con.query(sql, _rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                } else {
                    promise.complete(rowSetToJson(_rr.result()));
                }
            });
        });
        return promise.future();
    }

    protected Future<List<JsonObject>> executeQuery(final String sql, Tuple param) {

        final Promise<List<JsonObject>> promise = Promise.promise();

        getConnection().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final SqlConnection con = _rs.result();
            con.preparedQuery(sql, param, _rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                } else {
                    promise.complete(rowSetToJson(_rr.result()));
                }
                con.close();
            });
        });
        return promise.future();
    }

    protected Future<Optional<JsonObject>> findAny(String sql, Tuple param) {
        final Promise<Optional<JsonObject>> promise = Promise.promise();

        executeQuery(sql, param).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> result = _rs.result();
            final Optional<JsonObject> optional;
            if (result.isEmpty()) {
                optional = Optional.empty();
            } else {
                optional = Optional.of(result.get(0));
            }
            promise.complete(optional);
        });
        return promise.future();
    }

    protected Future<Integer> executeUpdate(String sql, Tuple param) {
        final Promise<Integer> promise = Promise.promise();

        getConnection().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final SqlConnection con = _rs.result();
            con.preparedQuery(sql, param, _rr -> {
                if (_rr.failed()) {
                    promise.fail(_rr.cause());
                } else {
                    rowSetToJson(_rr.result()).forEach(_item -> {
                        System.out.println(_item.encodePrettily());
                    });
                    promise.complete(1);
                }
            });
        });

        return promise.future();
    }

    protected Future<Integer> batchUpdate(String sql, List<Tuple> tuples) {
        final Promise<Integer> promise = Promise.promise();
        getConnection().setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final SqlConnection con = _rs.result();
            con.preparedBatch(sql, tuples, _rr -> {
              if (_rr.failed()){
                  promise.fail(_rr.cause());
                  return;
              }
              con.close();
              promise.complete(1);
            });
        });
        return promise.future();
    }

    /**
     * 获取连接
     *
     * @return 返回连接对象
     */
    protected Future<SqlConnection> getConnection() {
        final Promise<SqlConnection> promise = Promise.promise();
        client.getConnection(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result());
        });
        return promise.future();
    }

    private List<JsonObject> rowSetToJson(RowSet rowSet) {
        final List<JsonObject> list = new ArrayList<>();
        rowSet.forEach(_row -> {
            final JsonObject temp = new JsonObject();
            for (int i = 0; i < _row.size(); i++) {
                final Object val = _row.getValue(i);
                if (val != null) {
                    if (val instanceof LocalDateTime) {
                        temp.put(_row.getColumnName(i), val.toString());
                    } else {
                        temp.put(_row.getColumnName(i), val);
                    }
                }
            }
            list.add(temp);
        });
        return list;
    }
}
