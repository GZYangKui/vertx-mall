package cn.navigational.admin.client.service;

import io.vertx.core.Vertx;

public class HttpService {
    private static String token = "";
    private static Vertx vertx = Vertx.vertx();

    public static void clearToken() {
        token = "";
    }

    public static Vertx get() {
        return vertx;
    }
}
