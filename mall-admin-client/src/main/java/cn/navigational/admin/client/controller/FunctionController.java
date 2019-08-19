package cn.navigational.admin.client.controller;

import cn.navigational.admin.client.monitors.WindowDragMonitor;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;


import java.net.URL;
import java.util.ResourceBundle;

public class FunctionController implements Initializable {
    @FXML
    private JFXTreeView navigatorMenu;
    @FXML
    private JFXComboBox<Label> operationBox;
    @FXML
    private HBox navigatorBar;

    private final Popup popup = new PopMenu();

    private boolean isRegister = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //弹出菜单
    @FXML
    private void requestContextMenu(MouseEvent e) {
        if (e.getButton() != MouseButton.SECONDARY) {
            return;
        }
        final HBox box = (HBox) e.getSource();
        popup.show(box.getScene().getWindow());
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

    final class PopMenu extends Popup {
        private Label maximization = new Label("最大化");
        private Label minimize = new Label("最小化");
        private Label normal = new Label("正常窗口");
        private JFXListView<Label> list = new JFXListView<>();

        private PopMenu() {
            list.getItems().addAll(maximization, normal, minimize);
            getContent().add(list);
            setAutoHide(true);
            setAutoFix(true);
            list.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println(newValue);
                this.hide();
            });
        }
    }
}
