package cn.navigational.admin.client;

import cn.navigational.admin.client.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("管理员登录");
        stage.setIconified(false);
        stage.setHeight(300);
        stage.setWidth(200);
        new LoginView().setStage(stage);
    }
}
