package cn.navigational.model;

/**
 *
 * 权限和角色关联表
 *
 * @author YangKui
 * @since 1.0
 *
 */
public class RoleAndPermission {
    //关联信息id
    private int id;
    //角色id
    private int roldId;
    //权限id
    private int permissionId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoldId() {
        return roldId;
    }

    public void setRoldId(int roldId) {
        this.roldId = roldId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
