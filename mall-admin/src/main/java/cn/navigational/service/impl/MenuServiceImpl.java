package cn.navigational.service.impl;

import cn.navigational.dao.MenuDao;
import cn.navigational.service.MenuService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MenuServiceImpl implements MenuService {
    private MenuDao dao;

    public MenuServiceImpl(Vertx vertx, JsonObject config) {
        dao = new MenuDao(vertx,config);
    }
}
