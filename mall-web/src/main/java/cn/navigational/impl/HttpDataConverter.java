package cn.navigational.impl;

import cn.navigational.base.HttpValidator;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.*;

public class HttpDataConverter extends HttpValidator {
    @Override
    public void handle(RoutingContext routingContext) {

        final HttpServerRequest request = routingContext.request();
        final String uri = request.path();
        final JsonObject temp = new JsonObject();
        final int index = uri.indexOf("/", 5);
        final String requestApi = uri.substring(0, index);
        temp.put(REQUEST_API, requestApi);

        temp.put(ACTION, uri.substring(index, uri.length()));

        temp.put(QUERY, getQuery(routingContext));

        temp.put(HEADERS, getHeaders(request));

        if (request.method() == HttpMethod.POST) {
            final String str = routingContext.getBodyAsString();
            if (str.startsWith("{")) {
                temp.put(BODY, routingContext.getBodyAsJson());
            } else if (str.startsWith("[")) {
                temp.put(BODY, routingContext.getBodyAsJsonArray());
            } else {
                temp.put(BODY, routingContext.getBody().getBytes());
            }
        }

        routingContext.setBody(temp.toBuffer());

        routingContext.next();
    }

    private JsonObject getQuery(final RoutingContext routingContext) {
        final JsonObject query = new JsonObject();
        routingContext.queryParams().forEach(_rs -> query.put(_rs.getKey(), _rs.getValue()));
        return query;
    }

    private JsonObject getHeaders(HttpServerRequest routingContext) {
        final JsonObject headers = new JsonObject();
        routingContext.headers().forEach(_rs -> headers.put(_rs.getKey(), _rs.getValue()));
        return headers;
    }

    public static HttpValidator create() {
        return new HttpDataConverter();
    }
}
