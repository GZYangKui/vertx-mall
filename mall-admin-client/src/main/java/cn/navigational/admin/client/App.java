package cn.navigational.admin.client;

import cn.navigational.admin.client.views.LoginView;
import io.vertx.core.Vertx;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    private final static Vertx vertx = Vertx.vertx();

    @Override
    public void start(Stage stage) throws Exception {
        new LoginView();
    }

    @Override
    public void stop() throws Exception {
        vertx.close();
    }

    public static Vertx getVertx() {
        return vertx;
    }
}
