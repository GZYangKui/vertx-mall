package cn.navigational.admin.client.views;

import cn.navigational.admin.client.utils.ResourceUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FunctionView extends Stage {

    public FunctionView() {
        final Parent root = ResourceUtils.loadFXML("fxml/function.fxml");
        Scene scene = new Scene(root);
        setScene(scene);
        setWidth(1200);
        setHeight(800);
        setTitle("vertx-mall后台管理系统");
        initStyle(StageStyle.UNDECORATED);
        show();
    }
}
