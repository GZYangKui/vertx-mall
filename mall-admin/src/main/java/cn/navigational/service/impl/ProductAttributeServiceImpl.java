package cn.navigational.service.impl;

import cn.navigational.dao.ProductAttributeDao;
import cn.navigational.service.ProductAttributeService;
import cn.navigational.service.ProductService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ProductAttributeServiceImpl implements ProductAttributeService {
    private ProductAttributeDao dao;

    public ProductAttributeServiceImpl(Vertx vertx, JsonObject config) {
        dao = new ProductAttributeDao(vertx,config);
    }
}
