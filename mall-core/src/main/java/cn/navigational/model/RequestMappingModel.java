package cn.navigational.model;

import io.vertx.core.http.HttpMethod;

import java.lang.reflect.Method;

public class RequestMappingModel {
    //请求url
    private String api;
    //http请求方法
    private HttpMethod httpRequestMethod;
    //待执行方法
    private Method method;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public HttpMethod getHttpRequestMethod() {
        return httpRequestMethod;
    }

    public void setHttpRequestMethod(HttpMethod httpRequestMethod) {
        this.httpRequestMethod = httpRequestMethod;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
