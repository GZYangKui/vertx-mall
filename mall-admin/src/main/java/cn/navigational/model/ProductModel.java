package cn.navigational.model;

/**
 * 商品属性
 *
 * @author YangKui
 * @since 1.0
 */
public class ProductModel {
    //商品id
    private long id;
    //品牌id
    private long brandId;
    //产品分类id
    private long productCategoryId;
    //商品标题
    private String title;
    //商品主图路径
    private String pic;
    //产品编号
    private String productSn;
    //删除状态
    private int deleteStatus;
    //是否新货状态
    private int newStatus;
    //推荐状态
    private int recommendStatus;
    //是否审核
    private int verifyStatus;
    //价格(以分为单位)
    private long price;
    //销售量
    private long sale;
    //品牌名称
    private String brandName;
    //分类名称
    private String productCategoryName;
    //子标题
    private String subTitle;
    //商品排序
    private long sort;
    //商品描述
    private String description;
    //商品画廊各个图片以逗号区分
    private String albumPics;
    //获得成长值
    private long giftGrowth;
    //获得返点数
    private long giftPoint;
    //积分使用限制
    private long usePointLimit;
    //市场价格
    private long originaPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public long getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(long giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public long getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(long giftPoint) {
        this.giftPoint = giftPoint;
    }

    public long getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(long usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public long getOriginaPrice() {
        return originaPrice;
    }

    public void setOriginaPrice(long originaPrice) {
        this.originaPrice = originaPrice;
    }
}
