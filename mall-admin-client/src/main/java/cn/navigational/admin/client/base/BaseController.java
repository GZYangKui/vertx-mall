package cn.navigational.admin.client.base;

import cn.navigational.admin.client.annotation.Layout;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class BaseController {
    private Stage stage = new Stage();

    private Parent root;

    public BaseController() {
        final Layout layout = this.getClass().getAnnotation(Layout.class);
        if (layout == null) {
            throw new RuntimeException("请指定fxml文件");
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Parent getRoot() {
        return root;
    }
}
