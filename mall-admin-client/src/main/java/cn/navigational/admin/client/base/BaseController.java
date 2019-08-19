package cn.navigational.admin.client.base;

import cn.navigational.admin.client.annotation.Layout;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public abstract class BaseController implements Initializable {
    private Stage stage = new Stage();

    private Parent root;

    public BaseController() {
        final Layout layout = this.getClass().getAnnotation(Layout.class);
        if (layout == null) {
            throw new RuntimeException("请指定fxml文件");
        }
        final String url = layout.value();
        if (url.trim().equals("")) {
            throw new RuntimeException("url不能为空");
        }
        final FXMLLoader loader =new  FXMLLoader();
        try {
            root = loader.load(ClassLoader.getSystemResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
