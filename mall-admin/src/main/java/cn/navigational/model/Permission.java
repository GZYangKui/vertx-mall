package cn.navigational.model;

/**
 *
 * 后台管理权限
 *
 * @author YangKui
 * @since 1.0
 */
public class Permission {
    //权限id
    private int id;
    //权限名称
    private String name;
    //权限值
    private String value;
    //父级权限id
    private int parentId;
    //权限类型(0->目录；1->菜单；2->按钮（接口绑定权限))
    private int type;
    //权限状态(0->启用 1->未启用)
    private int status;
    //创建时间
    private String createTime;
    //排序
    private long sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }
}
