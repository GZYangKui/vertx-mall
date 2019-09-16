package cn.navigational.routers;

import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.service.MenuService;
import cn.navigational.service.impl.MenuServiceImpl;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 *
 * 商城管理后台菜单管理路由{@link cn.navigational.model.Menu}
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/menu")
public class MenuRouter extends RouterVerticle {
    private MenuService service;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new MenuServiceImpl(vertx,config());
    }

    @RouterMapping(api = "/list")
    public void list(final EBRequest request, final Promise<JsonObject> response){

    }

}
