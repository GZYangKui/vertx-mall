package cn.navigational.model;

import io.vertx.core.json.JsonObject;

import static cn.navigational.config.Constants.PAGE;
import static cn.navigational.config.Constants.PAGE_SIZE;

public class Paging {
    private int initOffset;
    private int pageSize;

    public Paging(JsonObject query) {
        int page = Integer.parseInt(query.getString(PAGE));
        pageSize = Integer.parseInt(query.getString(PAGE_SIZE));
        initOffset = (page - 1) * pageSize;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public void setInitOffset(int initOffset) {
        this.initOffset = initOffset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
