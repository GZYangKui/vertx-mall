package cn.navigational.admin.client.controller;

import cn.navigational.admin.client.monitors.WindowDragMonitor;
import cn.navigational.admin.client.service.HttpService;
import cn.navigational.admin.client.views.LoginView;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.net.URL;
import java.util.ResourceBundle;

public class FunctionController implements Initializable {
    @FXML
    private JFXTreeView navigatorMenu;
    @FXML
    private JFXComboBox<Label> operationBox;
    @FXML
    private HBox navigatorBar;

    private boolean isRegister = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operationBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            final String label = newValue.getText();
            final Stage stage = (Stage) navigatorBar.getScene().getWindow();
            switch (label) {
                case "首页":
                    break;
                case "注销":
                    stage.close();
                    new LoginView();
                    break;
                default:
            }
        }));
    }

    //弹出菜单
    @FXML
    private void requestContextMenu(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) {
            return;
        }
        final HBox box = (HBox) e.getSource();
        final Popup popup = new PopMenu();
        final Window window = box.getScene().getWindow();
        popup.setX(e.getX() + window.getX() + 300);
        popup.setY(e.getY() + window.getY() + 25);
        popup.show(window);
    }

    @FXML
    private void register(MouseEvent e) {
        if (isRegister) {
            return;
        }
        isRegister = true;
        HBox obj = (HBox) e.getSource();
        WindowDragMonitor.register(obj.getScene().getWindow(), obj);
    }

    //弹出菜单
    private final class PopMenu extends Popup {
        private final Label maximization = new Label("最大化");
        private final Label minimize = new Label("最小化");
        private final Label normal = new Label("正常窗口");
        private final JFXListView<Label> list = new JFXListView<>();

        private PopMenu() {
            list.getItems().addAll(maximization, normal, minimize);
            getContent().add(list);
            setAutoHide(true);
            setAutoFix(true);
            setWidth(50);
            setHeight(50);
            list.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                final String label = newValue.getText();
                final Stage stage = (Stage) navigatorBar.getScene().getWindow();
                switch (label) {
                    case "最大化":
                        stage.setMaximized(true);
                        break;
                    case "最小化":
                        stage.setIconified(true);
                        break;
                    default:
                        stage.setMaximized(false);
                }
                hide();
            }));
        }
    }
}
