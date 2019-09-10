package cn.navigational.model;

import java.util.List;

/**
 * 后台管理系统菜单
 *
 * @author YangKui
 * @since 1.0
 */
public class Menu {
    private int id;
    //菜单名称
    private String label;
    //菜单地址
    private String url;
    //菜单描述
    private String description;
    //菜单图标
    private String icon;
    //菜单状态(0->禁用 1->启用)
    private int status;
    //父菜单
    private Menu parent;
    //子菜单
    private List<Menu> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
