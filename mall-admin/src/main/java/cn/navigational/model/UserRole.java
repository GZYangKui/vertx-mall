package cn.navigational.model;

/**
 *
 * 用户角色
 *
 * @author YangKui
 * @since 1.0
 *
 */
public class UserRole {
    //角色id
    private int id;
    //角色名称
    private String name;
    //角色描述
    private String description;
    //创建时间
    private String createTime;
    //角色状态 0->禁用 1->启用
    private int status;
    //排序
    private int sort;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
