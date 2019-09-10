package cn.navigational.model;

/**
 *
 * 后台管理用户和角色关联信息
 *
 * @author YangKui
 * @since 1.0
 *
 */
public class AdminAndRole {
    //关联id
    private int id;
    //管理员id
    private int adminId;
    //角色id
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
