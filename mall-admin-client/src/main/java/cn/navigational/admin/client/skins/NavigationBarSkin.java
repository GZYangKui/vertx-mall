package cn.navigational.admin.client.skins;

import cn.navigational.admin.client.controls.NavigationBar;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;

public class NavigationBarSkin implements Skin<NavigationBar> {

    private final NavigationBar bar;

    private final HBox hBox = new HBox();

    public NavigationBarSkin(NavigationBar bar) {
        this.bar = bar;
        renderNode();
    }

    //渲染节点
    private void renderNode() {
        final ObservableList<TreeItem> obs = bar.getItems();
        obs.forEach(item -> {
            final Label separator = new Label(bar.getSeparator());
            separator.setAlignment(Pos.CENTER);
            final JFXButton action = new JFXButton(item.getValue().toString());
            hBox.getChildren().addAll(separator, action);
        });
        /****obs移除元素监听******/
        obs.removeListener((ListChangeListener<TreeItem>) c -> {
            System.out.println("移除元素"+c.getRemoved());
        });
    }

    @Override
    public NavigationBar getSkinnable() {
        return bar;
    }

    @Override
    public Node getNode() {
        return hBox;
    }

    @Override
    public void dispose() {
        bar.getItems().clear();
    }
}
