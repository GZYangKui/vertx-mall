package cn.navigational.model;

/**
 *
 * 管理员用户账号
 * @author YangKui
 * @since 1.0
 */
public class AdminUser {
    //管理员id
    private int id;
    //管理员用户名
    private String username;
    //管理员密码
    private String password;
    //管理员头像图标
    private String icon;
    //管理员email
    private String email;
    //管理员昵称
    private String nickName;
    //账户创建时间
    private String createTime;
    //账户状态(0->禁用 1->启用)
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
