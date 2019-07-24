package cn.navigational.model;

import io.vertx.core.json.JsonObject;

public class JwtUser {
    //令牌发行机构
    private String iss;
    //过期时间
    private long exp;
    //发行时间
    private long iat;
    //用户id
    private long userId;
    //用户名
    private String username;


    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return JsonObject.mapFrom(this).encodePrettily();
    }
}
