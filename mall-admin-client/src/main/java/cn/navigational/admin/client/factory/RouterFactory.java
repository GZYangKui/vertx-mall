package cn.navigational.admin.client.factory;

import cn.navigational.admin.client.controls.RouterItem;
import cn.navigational.admin.client.model.RouterModel;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;

import java.util.Objects;

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
        final JsonObject root = router.getJsonObject(ROOT);
        analysis(root, null);
        return this;
    }

    /**
     * 通过递归方式解析子节点
     *
     * @param obj    路由信息
     * @param parent 父节点
     */
    private void analysis(JsonObject obj, RouterItem parent) {
        final JsonArray children = obj.getJsonArray(CHILDREN, new JsonArray());
        final RouterModel model = obj.mapTo(RouterModel.class);
        final RouterItem item = new RouterItem(model);
        if (Objects.isNull(parent)) {
            root = item;
            parent = item;
        } else {
            parent.getChildren().add(item);
        }
        TreeItem<HBox> temp = null;
        int grade = 0;

        //判断节点深度
        do {
            item.getModel().setGrade(grade);
            grade++;
            temp = parent.getParent();
        } while (temp != null);

        if (children.isEmpty()) {
            return;
        }
        for (int i = 0; i < children.size(); i++) {
            final JsonObject o = children.getJsonObject(i);
            analysis(o, parent);
        }
    }

    public RouterItem getRoot() {
        return root;
    }

    public static RouterFactory create(JsonObject router) {
        return new RouterFactory(router);
    }
}
