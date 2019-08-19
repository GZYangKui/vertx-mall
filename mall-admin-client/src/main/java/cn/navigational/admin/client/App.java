package cn.navigational.admin.client;

import cn.navigational.admin.client.controller.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final LoginController controller = new LoginController();
        final Scene scene = new Scene(controller.getRoot(), 500, 300);
        stage.setScene(scene);
        stage.setTitle("管理员登录");
        stage.setIconified(false);
        stage.show();
    }
}
