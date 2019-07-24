package cn.navigational.dao.impl;

import cn.navigational.base.BaseDao;
import cn.navigational.dao.CouponDao;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class CouponDaoImpl extends BaseDao implements CouponDao {
    public CouponDaoImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }
}
