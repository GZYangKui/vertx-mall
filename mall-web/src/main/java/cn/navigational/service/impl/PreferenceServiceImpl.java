package cn.navigational.service.impl;

import cn.navigational.base.BaseService;
import cn.navigational.dao.PreferenceDao;
import cn.navigational.dao.ProductDao;
import cn.navigational.dao.impl.PreferenceDaoImpl;
import cn.navigational.dao.impl.ProductDaoImpl;
import cn.navigational.model.Paging;
import cn.navigational.service.PreferenceService;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

public class PreferenceServiceImpl extends BaseService implements PreferenceService {

    private final PreferenceDao dao = new PreferenceDaoImpl(vertx, config);
    private final ProductDao productDao = new ProductDaoImpl(vertx,config);

    public PreferenceServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    @Override
    public Future<JsonObject> list(JsonObject obj) {
        final Promise<JsonObject> promise = Promise.promise();

        final Paging paging = getPaging(obj);

        dao.getList(paging).setHandler(_rs -> {
            if (_rs.failed()) {
                promise.fail(_rs.cause());
                return;
            }
            final List<JsonObject> list = _rs.result();
            if (list.isEmpty()) {
                promise.complete(responseSuccessJson(List.of()));
                return;
            }
            //获取优选专题id
            final List<Integer> ids = list.stream().map(_rr->_rr.getInteger("id")).collect(Collectors.toList());
            //获取关联商品id
            dao.getRelateProductId(ids).setHandler(_rr->{
                if (_rr.failed()){
                    promise.fail(_rr.cause());
                    return;
                }
                //专题关联信息
                final List<JsonObject> temp = _rr.result();
                //获取商品id
                final List<Integer> products = temp.stream().map(_r->_r.getInteger("product_id")).collect(Collectors.toList());
                //获取商品信息
                productDao.list(products).setHandler(_rrr->{
                   if (_rrr.failed()){
                       promise.fail(_rrr.cause());
                       return;
                   }
                   //TODO 实现集合归纳
                });
            });
        });
        return promise.future();
    }
}
