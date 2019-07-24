package cn.navigational.utils;

import cn.navigational.enums.EventBusDataType;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static cn.navigational.config.Constants.*;

public class ResponseUtils {
    public static JsonObject responseFailed(String msg, int code) {
        return responseTemplate(msg, code, false, EventBusDataType.JSON);
    }

    public static JsonObject responseSuccessJson() {
        return responseTemplate("ok", 200, true, EventBusDataType.JSON);
    }

    public static JsonObject responseSuccessJson(Object data) {
        final JsonObject temp = responseTemplate("ok", 200, true, EventBusDataType.JSON);
        temp.put(DATA, data);
        return temp;
    }

    public static JsonObject responsePng(byte[] bytes) {
        final JsonObject temp = responseTemplate("ok", 200, true, EventBusDataType.PNG);
        temp.put(DATA, bytes);
        return temp;
    }

    public static JsonObject responseTemplate(String msg, int code, boolean flag, EventBusDataType type) {
        final JsonObject temp = new JsonObject();
        temp.put(CODE, code);
        temp.put(FLAG, flag);
        temp.put(MESSAGE, msg);
        temp.put(TIME_STAMP, System.currentTimeMillis());
        temp.put(TYPE, type);
        temp.put(HEADERS, getHeaders(type));
        return temp;
    }

    private static JsonObject getHeaders(EventBusDataType type) {
        final JsonObject headers = new JsonObject();
        if (type == EventBusDataType.JSON) {
            headers.put("Content-Type", "application/json;charset=utf-8");
        }
        if (type == EventBusDataType.PNG) {
            headers.put("Content-Type", "image/png");
        }
        return headers;
    }

    public static void response(RoutingContext rcx, JsonObject info) {
        final HttpServerResponse response = rcx.response();
        final EventBusDataType type = EventBusDataType.valueOf(info.getString(TYPE));
        final JsonObject headers = (JsonObject) info.remove(HEADERS);
        headers.forEach(_header -> response.putHeader(_header.getKey(), _header.getValue().toString()));
        if (type == EventBusDataType.JSON) {
            response.end(info.toBuffer());
        } else if (type == EventBusDataType.PNG) {
            final byte[] bytes = info.getBinary(DATA);
            response.end(Buffer.buffer(bytes));
        } else {
            //TODO MORE
        }
    }
}
