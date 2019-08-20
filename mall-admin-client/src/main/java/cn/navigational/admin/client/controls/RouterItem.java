package cn.navigational.admin.client.controls;

import cn.navigational.admin.client.model.RouterModel;
import cn.navigational.admin.client.utils.ResourceUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;


public class RouterItem extends TreeItem<HBox> {
    private final RouterModel model;
    private final HBox box = new HBox();

    private final static String DEFAULT_STYLE_CLASS = "router-item";

    //路由图标根路径
    private static final String ROOT_PATH = "assets" + File.separator + "router_icon" + File.separator;

    public RouterItem(RouterModel value) {
        this.model = value;
        renderNode();
    }

    private void renderNode() {
        final ImageView icon = new ImageView(ResourceUtils.loadImage(ROOT_PATH + model.getIcon(), 30.0, 30.0));
        final Label title = new Label(model.getTitle());
        box.getChildren().addAll(icon, title);
        box.getStyleClass().add(DEFAULT_STYLE_CLASS);
        box.getStylesheets().add(getUserAgentStyle().toExternalForm());
        setValue(box);
    }

    public URL getUserAgentStyle() {
        return ResourceUtils.loadCSS("css/controls/router-item.css");
    }

}


