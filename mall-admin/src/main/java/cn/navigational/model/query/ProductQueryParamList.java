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
    private int pageIndex;
    //分页查询页面数据尺寸
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
