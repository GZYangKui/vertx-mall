package cn.navigational.admin.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("管理员登录");
        stage.setIconified(false);
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/login.fxml"));
        final Scene scene = new Scene(root,800,500);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
