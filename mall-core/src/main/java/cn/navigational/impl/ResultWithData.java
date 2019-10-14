package cn.navigational.impl;

import com.qihaoljl.base.BasicResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 响应http请求并携带数据
 *
 * @author YangKui
 * @since 1.0
 */
public class ResultWithData<T> extends BasicResult {
    //自定义回复数据
    private List<T> data = new ArrayList<>();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static <T> BasicResult build(List<T> data, long totalPage) {


        return build(data,totalPage,"获取数据列表成功");
    }

    public static <T> BasicResult build(List<T> data, long totalPage, String message) {
        ResultWithPage<T> resultWithData = new ResultWithPage<>();

        resultWithData.setData(data);
        resultWithData.setMessage(message);
        resultWithData.setTotalPage(totalPage);

        return resultWithData;
    }
}
