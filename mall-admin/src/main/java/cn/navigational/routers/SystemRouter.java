package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.service.SystemService;
import cn.navigational.service.impl.SystemServiceImpl;
import io.vertx.core.Promise;

/**
 * 系统管理类路由
 *
 * @author YangKui
 * @since 1.0
 */
@Verticle
@Router(api = "/api/system")
public class SystemRouter extends RouterVerticle {
    private SystemService service;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
        service = new SystemServiceImpl(vertx,config());
    }

}
