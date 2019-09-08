package cn.navigational.model;

/**
 *
 * 管理员登录日志
 * @author YangKui
 * @since 1.0
 */
public class LoginLogger {
    private int id;
    //管理员id
    private int adminId;
    //创建时间
    private String createTime;
    //ip地址
    private String ip;
    //浏览器类型
    private String userAgent;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
