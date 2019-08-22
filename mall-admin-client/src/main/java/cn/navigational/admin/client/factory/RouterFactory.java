package cn.navigational.admin.client.factory;

import cn.navigational.admin.client.controls.RouterItem;
import cn.navigational.admin.client.model.RouterModel;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;

import static cn.navigational.admin.client.config.Constants.*;

/**
 * 采用工厂方法集中注册路由
 */
public class RouterFactory {
    private final JsonObject router;

    private RouterItem root;

    private RouterFactory(JsonObject router) {
        this.router = router;
    }

    //执行解析json成TreeItem工作
    public RouterFactory build() {
        final JsonObject rootInfo = router.getJsonObject(ROOT);
        final RouterModel model = rootInfo.mapTo(RouterModel.class);
        root = new RouterItem(model);
        analysis(rootInfo, root);
        return this;
    }

    /**
     * 通过递归方式解析子节点root
     *
     * @param obj    路由信息
     * @param parent 父节点
     */
    private void analysis(JsonObject obj, RouterItem parent) {
        final JsonArray children = obj.getJsonArray(CHILDREN, new JsonArray());
        for (int i = 0; i < children.size(); i++) {

            final JsonObject it = children.getJsonObject(i);
            final RouterModel model = it.mapTo(RouterModel.class);
            final RouterItem item = new RouterItem(model);

            parent.getChildren().add(item);

            analysis(it, item);
        }
    }

    public RouterItem getRoot() {
        return root;
    }

    public static RouterFactory create(JsonObject router) {
        return new RouterFactory(router);
    }
}
