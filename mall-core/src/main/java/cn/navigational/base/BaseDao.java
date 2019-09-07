package cn.navigational.base;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.*;


import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cn.navigational.config.Constants.*;

public class BaseDao {

    private final PgPool client;

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
     *
     * 执行查询
     *
     * @param sql sql语句
     * @return 返回json数据集合
     */
    protected Future<List<JsonObject>> executeQuery(final String sql) {
        final Promise<List<JsonObject>> promise = Promise.promise();
        client.query(sql, _rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(rowSetToJson(_rs.result()));
        });
        return promise.future();
    }

    protected Future<List<JsonObject>> executeQuery(final String sql, Tuple param) {

        final Promise<List<JsonObject>> promise = Promise.promise();

        client.preparedQuery(sql, param, _rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(rowSetToJson(_rs.result()));
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

        client.preparedQuery(sql, param, _rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().rowCount());
        });

        return promise.future();
    }

    /**
     * 插入单条数据
     *
     * @return 返回异步自增id
     */
    protected Future<Integer> insertSingle(final String sql, Tuple tuple) {
        final String reSql = sql + " RETURNING id";
        final Promise<Integer> promise = Promise.promise();
        client.preparedQuery(reSql, tuple, _rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
            } else {
                promise.complete(_rs.result().iterator().next().getInteger("id"));
            }
        });
        return promise.future();
    }

    /**
     * 批量更新
     *
     * @param sql    sql语句
     * @param tuples 数据集合
     * @return 返回被影响数据条目
     */
    protected Future<Integer> batchUpdate(String sql, List<Tuple> tuples) {
        final Promise<Integer> promise = Promise.promise();
        client.preparedBatch(sql, tuples, _rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            promise.complete(_rs.result().rowCount());
        });
        return promise.future();
    }

    /**
     * 拼接sql IN语法
     *
     * @param sb    sql语句
     * @param tuple sql参数
     * @param list  参数值
     */
    protected void pingIn(StringBuilder sb, Tuple tuple, List list, int initIndex) {
        for (int i = 0; i < list.size(); i++) {
            final int index = i + initIndex;
            sb.append("$").append(index);
            tuple.addValue(list.get(i));
            if (i == list.size() - 1) {
                sb.append(")");
            } else {
                sb.append(",");
            }
        }
    }

    /**
     * 获取连接(通常是要使用事物等操作才会主动获取连接否则使用client#...方法即可)
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

    /**
     * 将查出来的jdbc数据类型转换为json类型
     *
     * @param rowSet jdbc数据集合
     * @return json数据集合
     */
    private List<JsonObject> rowSetToJson(RowSet rowSet) {
        final List<JsonObject> list = new ArrayList<>();
        rowSet.forEach(_row -> {
            final JsonObject temp = new JsonObject();
            for (int i = 0; i < _row.size(); i++) {
                final Object val = _row.getValue(i);
                if (val != null) {
                    if (val instanceof LocalDateTime) {
                        temp.put(_row.getColumnName(i), val.toString());
                    } else if (val instanceof LocalDate) {
                        temp.put(_row.getColumnName(i), val.toString());
                    } else if (val instanceof LocalTime) {
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
