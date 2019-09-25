package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import io.vertx.core.Promise;

/**
 * 阿里云oss配置路由
 *
 * @author YangKUi
 * @since 1.0
 */
@Verticle
@Router(api = "/api/oss")
public class OSSRouter extends RouterVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

}
