package cn.navigational.admin.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FunctionView extends Stage {

    public FunctionView() {
        final Parent root;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/function.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        setScene(scene);
        setWidth(1200);
        setHeight(800);
        setTitle("vertx-mall后台管理系统");
        initStyle(StageStyle.UNDECORATED);
        show();
    }
}
