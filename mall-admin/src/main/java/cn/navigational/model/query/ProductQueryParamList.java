package cn.navigational.model.query;

import cn.navigational.model.Paging;

/**
 * 商品查询列表参数
 *
 * @author YangKui
 * @since 1.0
 */
public class ProductQueryParamList {

    //分页查询起始页
    private int pageNum;
    //分页查询页面数据尺寸
    private int pageSize;
    //关键字
    private String keyword;
    //发布状态
    private int publishStatus = -1;
    //审核状态
    private int verifyStatus = -1;
    //商品编号
    private String productSn;
    //分类id
    private int productCategoryId = -1;
    //品牌id
    private int brandId=-1;


    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
