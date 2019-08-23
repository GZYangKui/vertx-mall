package cn.navigational.admin.client.base;

import cn.navigational.admin.client.utils.ResourceUtils;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BaseRouteContent{

    private BorderPane container;

    //如果不是通过fxml实现 则该对象为null
    private Parent root = null;

    public BaseRouteContent(final String url) {
        root = ResourceUtils.loadFXML(url);
        container = (BorderPane) root.lookup("#container");
    }

    public BaseRouteContent() {
        container = new BorderPane();
    }

    //进入场景图时调用
    public void initState() {
        //TODO OVERRIDE
    }

    //移除场景图时调用
    public void stop() {
        //TODO OVERRIDE
    }

    public BorderPane getContainer() {
        return container;
    }
}
