package cn.navigational.dao;

import cn.navigational.base.BaseDao;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Tuple;

import java.util.List;

public class MemberLevelDao extends BaseDao {
    public MemberLevelDao(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public Future<List<JsonObject>> getList(int defaultStatus) {
        String sql = "SELECT id,name,growth_point AS \"growthPoint\",default_status AS \"defaultStatus\"," +
                "free_freight_point AS \"freeFreigthPoint\",comment_growth_point AS \"commentGrowthPoint\",priviledge_free_freight AS \"priviledgeFreeFreigth\"," +
                "priviledge_sign_in AS \"priviledgeSignIn\",priviledge_comment AS \"priviledge\",priviledge_promotion AS \"" +
                "priviledgePromotion\",priviledge_member_price AS \"priviledgeMemberPrice\",priviledge_birthday AS \"" +
                "priviledgeBirthday\",note  FROM member_level WHERE default_status=$1";
        return executeQuery(sql, Tuple.of(defaultStatus));
    }
}
