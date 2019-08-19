package cn.navigational.admin.client;

import cn.navigational.admin.client.service.HttpService;
import cn.navigational.admin.client.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new LoginView();
    }

    @Override
    public void stop() throws Exception {
        HttpService.get().close();
    }
}
