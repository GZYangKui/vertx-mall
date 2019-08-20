package cn.navigational.model;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

import static cn.navigational.config.Constants.*;

public class EBRequest {
    //eventBus地址信息
    private String eventAddress;

    //URL中的查询参数
    private JsonObject query;

    //HttpHeaders
    private JsonObject headers;

    //请求路径
    private String path;

    //请求方法
    private HttpMethod method;

    //请求body
    private Object body;

    private EBRequest() {
    }


    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public JsonObject getHeaders() {
        return headers;
    }

    public void setHeaders(JsonObject headers) {
        this.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public JsonObject getQuery() {
        return query;
    }

    public void setQuery(JsonObject query) {
        this.query = query;
    }

    public static EBRequest create(JsonObject obj) {
        final EBRequest ebRequest = new EBRequest();
        ebRequest.setPath(obj.getString(PATH));
        ebRequest.setHeaders(obj.getJsonObject(HEADERS));
        ebRequest.setMethod(HttpMethod.valueOf(obj.getString(HTTP_METHOD)));
        if (ebRequest.getMethod() == HttpMethod.POST) {
            ebRequest.setBody(obj.getValue(BODY));
        }
        ebRequest.setEventAddress(obj.getString(EVENT_ADDRESS));
        System.out.println(JsonObject.mapFrom(ebRequest).encodePrettily());
        return ebRequest;
    }
}
