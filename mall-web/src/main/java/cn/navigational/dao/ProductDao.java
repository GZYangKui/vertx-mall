package cn.navigational.dao;

import cn.navigational.model.Paging;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;


public interface ProductDao {
    Future<List<JsonObject>> list(Paging paging);
}
