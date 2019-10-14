package cn.navigational.impl;

import cn.navigational.base.BasicResult;

public class ResultWithSingleData<T> extends BasicResult {
    //单条数据
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BasicResult build(T data) {
        ResultWithSingleData<T> resultWithData = new ResultWithSingleData<>();

        resultWithData.setData(data);

        return resultWithData;
    }

    public static <T> BasicResult build(T data, String message) {
        ResultWithSingleData<T> resultWithData = new ResultWithSingleData<>();

        resultWithData.setData(data);
        resultWithData.setMessage(message);

        return resultWithData;
    }
}
