package cn.navigational.admin.client.skins;

import cn.navigational.admin.client.controls.NavigationBar;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
        for (int i = 0; i < obs.size(); i++) {
            final TreeItem item = obs.get(i);
            final Label separator = new Label(bar.getSeparator());
            final JFXButton action = new JFXButton(item.getValue().toString());
            separator.setAlignment(Pos.CENTER);
            if (bar.getOrientation() == Pos.CENTER_LEFT) {
                if (i == 0) {
                    hBox.getChildren().add(action);
                } else {
                    hBox.getChildren().addAll(separator, action);
                }
            } else {
                if (i == obs.size() - 1) {
                    hBox.getChildren().add(action);
                } else {
                    hBox.getChildren().addAll(action, separator);
                }
            }
        }

        /****obs移除元素监听******/
        obs.removeListener((ListChangeListener<TreeItem>) c -> {
            System.out.println("移除元素" + c.getRemoved());
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
