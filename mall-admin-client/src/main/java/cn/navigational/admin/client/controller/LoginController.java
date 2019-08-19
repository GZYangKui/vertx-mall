package cn.navigational.admin.client.controller;

import cn.navigational.admin.client.monitors.WindowDragMonitor;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    private boolean isRegister = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void register(MouseEvent e) {
        if (isRegister) {
            return;
        }
        isRegister = true;
        HBox obj = (HBox) e.getSource();
        WindowDragMonitor.register(obj.getScene().getWindow(), obj);
    }

    @FXML
    public void login() {
        System.out.println("---------");
    }

    @FXML
    public void exit() {
        Platform.exit();
    }
}
