package cn.navigational.model.query;

import cn.navigational.model.Paging;

/**
 * 商品查询列表参数
 *
 * @author YangKui
 * @since 1.0
 */
public class ProductQueryParamList {
    //分页查询参数
    private Paging paging;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
