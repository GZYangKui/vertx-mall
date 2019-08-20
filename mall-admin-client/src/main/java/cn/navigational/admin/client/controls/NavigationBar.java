package cn.navigational.admin.client.controls;

import cn.navigational.admin.client.skins.NavigationBarSkin;
import cn.navigational.admin.client.utils.ResourceUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeItem;

import java.net.URL;


public class NavigationBar extends Control {

    //controls默认样式
    private static final String DEFAULT_CLASS_STYLE = "navigation-bar";

    //默认分隔符
    private String separator = "/";

    //分隔符方向
    private Pos orientation = Pos.CENTER_LEFT;

    private ObservableList<TreeItem> items = FXCollections.observableArrayList();

    public NavigationBar() {
        getStyleClass().add(DEFAULT_CLASS_STYLE);
        getStylesheets().add(getUserAgentStyle().toExternalForm());
    }

    public NavigationBar(ObservableList<TreeItem> items) {
        this();
        this.items = items;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new NavigationBarSkin(this);
    }

    public ObservableList<TreeItem> getItems() {
        return items;
    }

    public void setItems(ObservableList<TreeItem> items) {
        this.items = items;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    //获取css url
    public URL getUserAgentStyle() {
        return ResourceUtils.loadCSS("css/controls/navigation-bar.css");
    }

    public Pos getOrientation() {
        return orientation;
    }

    public void setOrientation(Pos orientation) {
        this.orientation = orientation;
    }
}
