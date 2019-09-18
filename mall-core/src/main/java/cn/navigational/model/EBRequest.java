package cn.navigational.model;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
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

    private JwtUser user;

    private JsonObject requestParam;

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

    public JwtUser getUser() {
        return user;
    }

    public void setUser(JwtUser user) {
        this.user = user;
    }

    public JsonObject getQuery() {
        return query;
    }

    public void setQuery(JsonObject query) {
        this.query = query;
    }

    public JsonObject getBodyAsJson() {
        return (JsonObject) body;
    }

    public JsonArray getBodyAsJsonArray() {
        return (JsonArray) body;
    }

    public byte[] getBodyAsByte() {
        return (byte[]) body;
    }

    public JsonObject getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(JsonObject requestParam) {
        this.requestParam = requestParam;
    }

    //获取单个请求参数
    public String getSingleRequestParam(String key) {
        return requestParam.getString(key, "");
    }

    //获取query中制定参数的值
    public String getQuery(String key) {
        return getQuery().getString(key);
    }

    //获取分页参数
    public Paging getPaging() {
        return new Paging(getQuery());
    }

    //获取指定httpHeader信息
    public String getHeader(String key) {
        return headers.getString(key, "");
    }

    public static EBRequest create(JsonObject obj) {

        EBRequest ebRequest = new EBRequest();
        ebRequest.setEventAddress(obj.getString(EVENT_ADDRESS));
        ebRequest.setPath(obj.getString(PATH));
        ebRequest.setHeaders(obj.getJsonObject(HEADERS));
        ebRequest.setMethod(HttpMethod.valueOf(obj.getString(HTTP_METHOD)));
        ebRequest.setQuery(obj.getJsonObject(QUERY));
        ebRequest.setRequestParam(obj.getJsonObject(REQUEST_PARAM));

        if (ebRequest.getMethod() == HttpMethod.POST) {
            ebRequest.setBody(obj.getValue(BODY));
        }

        final JsonObject user = obj.getJsonObject(USER);

        if (user != null) {
            final JwtUser jwtUser = user.mapTo(JwtUser.class);
            ebRequest.setUser(jwtUser);
        }
        return ebRequest;
    }
}
