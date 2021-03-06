package cn.navigational.model.config;

/***************************************************
 *   OSS配置信息,实际开发替换为自己的oss信息             *
 * *************************************************/
public class OssConfig {
    //签名超时时间(单位为秒)
    private int expire;
    //允许最大上传文件尺寸
    private long maxSize;
    //oss buckName
    private String bucketName;
    // oss endpoint
    private String endpoint;
    // oss accessKeyId
    private String accessKeyId;
    //oss secret
    private String secret;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
