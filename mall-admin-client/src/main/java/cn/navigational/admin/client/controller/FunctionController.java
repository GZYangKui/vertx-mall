package cn.navigational.admin.client.controller;


import cn.navigational.admin.client.base.BaseRouteContent;
import cn.navigational.admin.client.controls.NavigationBar;
import cn.navigational.admin.client.factory.RouterFactory;
import cn.navigational.admin.client.model.RouterModel;
import cn.navigational.admin.client.monitors.WindowDragMonitor;
import cn.navigational.admin.client.routers.NotFoundRouter;
import cn.navigational.admin.client.views.LoginView;
import com.jfoenix.controls.*;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static cn.navigational.admin.client.App.getVertx;

public class FunctionController implements Initializable {
    @FXML
    private JFXTreeView<HBox> leftDrawer;
    @FXML
    private JFXComboBox<Label> operationBox;
    @FXML
    private HBox topActionBox;
    @FXML
    private NavigationBar navigationBar;
    @FXML
    private VBox contentPane;

    private BaseRouteContent current;

    private static final NotFoundRouter a404 = new NotFoundRouter();

    private static final Logger logger = LogManager.getLogger(FunctionController.class);

    private boolean isRegister = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initRouter();
        navigationBar.getItems().addAll(new TreeItem("首页"), new TreeItem("销售额"), new TreeItem("今日销售额"));
        operationBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            final String label = newValue.getText();
            final Stage stage = (Stage) topActionBox.getScene().getWindow();
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

        leftDrawer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            navigationBar.push(newValue);
        });

        navigationBar.currentProperty.addListener((observable, oldValue, newValue) -> {
            setState(newValue.getModel());
        });
    }

    private void setState(RouterModel model) {
        contentPane.getChildren().clear();
        String redirectClass = model.getRedirect();
        if (Objects.isNull(redirectClass) || redirectClass.trim().equals("")) {
            a404.initState();
            contentPane.getChildren().add(a404.getContainer());
        }
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
                final Stage stage = (Stage) topActionBox.getScene().getWindow();
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

    private void initRouter() {
        final FileSystem fs = getVertx().fileSystem();
        fs.readFile("router/router.json", ar -> {
            if (ar.failed()) {
                logger.error("加载路由配置文件失败，原因:{}", ar.cause().getMessage());
                return;
            }
            final JsonObject router = ar.result().toJsonObject();
            final RouterFactory factory = RouterFactory.create(router).build();
            Platform.runLater(() -> leftDrawer.setRoot(factory.getRoot()));
        });
    }

}
