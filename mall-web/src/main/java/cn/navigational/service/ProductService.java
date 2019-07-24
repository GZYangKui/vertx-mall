package cn.navigational.service;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;


public interface ProductService {
    Future<JsonObject> list(JsonObject obj);
}
