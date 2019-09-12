package cn.navigational.service.impl;

import cn.navigational.dao.ProductDao;
import cn.navigational.service.ProductService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ProductServiceImpl implements ProductService {
    private ProductDao dao;

    public ProductServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductDao(vertx,config);
    }

}
