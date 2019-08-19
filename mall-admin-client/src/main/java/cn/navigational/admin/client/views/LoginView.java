package cn.navigational.admin.client.views;

import cn.navigational.admin.client.annotation.Layout;
import cn.navigational.admin.client.base.BaseController;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@Layout("fxml/login.fxml")
public class LoginView extends BaseController{
    public LoginView(Stage stage) {
        super(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
