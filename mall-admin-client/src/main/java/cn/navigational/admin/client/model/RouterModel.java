package cn.navigational.admin.client.model;


import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RouterModel {
    //标题
    private String title;

    //图标
    private String icon;

    //重定向操作类
    private String redirect;

    //节点深度
    private int depth = 0;

    //子路由
    private List children = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return JsonObject.mapFrom(this).encodePrettily();
    }
}
