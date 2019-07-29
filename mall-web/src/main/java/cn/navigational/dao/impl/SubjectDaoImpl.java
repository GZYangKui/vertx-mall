package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.SubjectDao;
import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.Optional;

public class SubjectDaoImpl extends BaseDao implements SubjectDao {
    public SubjectDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<List<JsonObject>> getList(Paging page, int cateId) {
        final String sql;
        final Tuple tuple = Tuple.tuple();
        tuple.addValue(1);
        if (cateId == 0) {
            sql = "SELECT * FROM mall_subject WHERE show_status=$1 LIMIT $2 OFFSET $3";

        } else {
            sql = "SELECT * FROM mall_subject WHERE show_status=$1 AND category_id=$2 LIMIT $3 OFFSET $4";
            tuple.addValue(cateId);
        }
        tuple.addValue(page.getPageSize());
        tuple.addValue(page.getInitOffset());
        return executeQuery(sql, tuple);
    }

    @Override
    public Future<List<JsonObject>> getSubjectCateInfo(List<Integer> ids) {
        final StringBuilder sb = new StringBuilder();
        final Tuple tuple = Tuple.tuple();
        sb.append("SELECT * FROM subject_category WHERE id IN(");
        for (int i = 0; i < ids.size(); i++) {
            final int index = i + 1;
            ;
            if (i != ids.size() - 1) {
                sb.append("$").append(index).append(",");
            } else {
                sb.append("$").append(index).append(")");
            }
            tuple.addValue(ids.get(i));
        }
        sb.append(" AND show_status=$").append(ids.size() + 1);
        tuple.addValue(1);
        return executeQuery(sb.toString(), tuple);
    }

    @Override
    public Future<List<JsonObject>> getSubjectCateList() {
        final String sql = "SELECT * FROM subject_category WHERE show_status=$1";
        return executeQuery(sql, Tuple.of(1));
    }

    @Override
    public Future<Optional<JsonObject>> getSubjectInfo(int subjectId) {
        final String sql = "SELECT * FROM mall_subject WHERE id=$1 AND show_status=$2";
        return findAny(sql, Tuple.of(subjectId, 1));
    }
}
