package cn.navigational.admin.client.views;

import cn.navigational.admin.client.service.HttpService;
import cn.navigational.admin.client.utils.ResourceUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginView extends Stage {
    public LoginView() {
        HttpService.clearToken();
        setTitle("管理员登录");
        setIconified(false);
        final Parent root = ResourceUtils.loadFXML("fxml/login.fxml");
        final Scene scene = new Scene(root, 800, 500);
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        show();
    }

}
