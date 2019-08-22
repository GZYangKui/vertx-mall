package cn.navigational.admin.client.controls;

import cn.navigational.admin.client.controller.FunctionController;
import cn.navigational.admin.client.skins.NavigationBarSkin;
import cn.navigational.admin.client.utils.ResourceUtils;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


public class NavigationBar extends Control {

    //controls默认样式
    private static final String DEFAULT_CLASS_STYLE = "navigation-bar";

    //默认分隔符
    private String separator = "/";

    //分隔符方向
    private Pos orientation = Pos.CENTER_LEFT;

    private ObservableList<TreeItem> items = FXCollections.observableArrayList();

    private final NavigationBarSkin navigationBarSkin;

    public NavigationBar() {
        navigationBarSkin = new NavigationBarSkin(this);
        getStyleClass().add(DEFAULT_CLASS_STYLE);
        getStylesheets().add(getUserAgentStyle().toExternalForm());
    }

    public void push(TreeItem item) {
        ArrayList<TreeItem> temp = new ArrayList();
        temp.add(item);
        TreeItem t = item.getParent();
        while (t != null) {
            temp.add(t);
            t = t.getParent();
        }
        items.clear();
        //反转集合
        Collections.reverse(temp);
        items.addAll(temp);
        setCurrentProperty((RouterItem) items.get(items.size() - 1));
        navigationBarSkin.updateNode();
    }

    //属性绑定
    public final ObjectProperty<RouterItem> currentProperty = new SimpleObjectProperty(this, "navigationBar");

    //设置当前属性值
    public void setCurrentProperty(RouterItem currentProperty) {
        this.currentProperty.set(currentProperty);
    }

    public RouterItem getCurrentProperty() {
        return currentProperty.get();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return navigationBarSkin;
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
