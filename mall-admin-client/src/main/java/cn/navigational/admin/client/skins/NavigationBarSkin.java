package cn.navigational.admin.client.skins;

import cn.navigational.admin.client.controls.NavigationBar;
import cn.navigational.admin.client.controls.RouterItem;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.stream.Collectors;

public class NavigationBarSkin implements Skin<NavigationBar> {

    private final NavigationBar bar;

    private final HBox hBox = new HBox();

    public NavigationBarSkin(NavigationBar bar) {
        this.bar = bar;
        renderNode();
    }

    //渲染节点
    private void renderNode() {
        hBox.getChildren().clear();
        final ObservableList<TreeItem> obs = bar.getItems();
        for (int i = 0; i < obs.size(); i++) {

            RouterItem item = (RouterItem) obs.get(i);
            Label separator = new Label(bar.getSeparator());
            JFXButton action = new JFXButton(item.getModel().getTitle());
            action.setOnMouseClicked(this::selectEvent);
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
    }

    public void updateNode() {
        renderNode();
    }

    private void selectEvent(MouseEvent e) {
        JFXButton target = (JFXButton) e.getSource();
        int index = hBox.getChildren().stream().filter(_r -> _r instanceof JFXButton).collect(Collectors.toList()).indexOf(target);
        int size = bar.getItems().size();
        if (index == size - 1) {
            return;
        }
        bar.getItems().remove(index + 1, bar.getItems().size());

        //获取当前节点
        RouterItem current = (RouterItem) bar.getItems().get(bar.getItems().size()-1);

        //更新属性绑定
        bar.setCurrentProperty(current);

        updateNode();
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
