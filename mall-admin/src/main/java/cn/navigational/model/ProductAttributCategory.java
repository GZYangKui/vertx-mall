package cn.navigational.model;

/**
 * 商品属性分类
 *
 * @author Yangkui
 * @since 1.0
 *
 */
public class ProductAttributCategory {
    //分类id
    private long id;
    //分类名称
    private String name;
    //属性数量
    private long attributeCount;
    //参数数量
    private long paramCount;
    //CAS版本号
    private long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(long attributeCount) {
        this.attributeCount = attributeCount;
    }

    public long getParamCount() {
        return paramCount;
    }

    public void setParamCount(long paramCount) {
        this.paramCount = paramCount;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
