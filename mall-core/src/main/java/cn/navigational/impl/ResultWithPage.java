package cn.navigational.impl;

/**
 * 响应Http请求,并携带数据和分页参数
 *
 * @author YangKui
 * @since 1.0
 */
public class ResultWithPage<T> extends ResultWithData<T> {
    //总页数
    private long totalPage;


    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
