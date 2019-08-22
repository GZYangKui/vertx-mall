package cn.navigational.admin.client.controls;

import cn.navigational.admin.client.model.RouterModel;
import cn.navigational.admin.client.utils.ResourceUtils;
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

    //默认字体大小
    private static final double DEFAULT_FONT_SIZE = 1.2;

    //默认图片尺寸大小
    private static final double DEFAULT_IMAGE_SIZE = 30.0;

    //路由图标根路径
    private static final String ROOT_PATH = "assets" + File.separator + "router_icon" + File.separator;

    public RouterItem(RouterModel value) {
        this.model = value;
        renderNode();
    }

    private void renderNode() {
        int scale = model.getDepth() * 2;

        double fontSize = DEFAULT_FONT_SIZE - model.getDepth() * 0.1;

        ImageView icon = new ImageView(ResourceUtils.loadImage(ROOT_PATH + model.getIcon(), DEFAULT_IMAGE_SIZE - scale, DEFAULT_IMAGE_SIZE - scale));
        Label title = new Label(model.getTitle());

        title.setStyle("-fx-font-size: "+fontSize+"em;");


        box.getChildren().addAll(icon, title);

        box.getStyleClass().add(DEFAULT_STYLE_CLASS);
        box.getStylesheets().add(getUserAgentStyle().toExternalForm());
        setValue(box);
    }

    public URL getUserAgentStyle() {
        return ResourceUtils.loadCSS("css/controls/router-item.css");
    }

    public RouterModel getModel() {
        return model;
    }


}


