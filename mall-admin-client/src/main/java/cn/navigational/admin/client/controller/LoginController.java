package cn.navigational.admin.client.controller;

import cn.navigational.admin.client.monitors.WindowDragMonitor;
import cn.navigational.admin.client.views.FunctionView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    public void login(ActionEvent e) {
        final Stage stage = (Stage) ((JFXButton) e.getSource()).getScene().getWindow();
        stage.close();
        new FunctionView();
    }

    @FXML
    public void exit(ActionEvent e) {
        e.consume();
        Platform.exit();
    }
}
