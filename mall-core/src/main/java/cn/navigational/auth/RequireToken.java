package cn.navigational.auth;

import cn.navigational.base.HttpValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;


import java.security.Key;

import static cn.navigational.config.Constants.USER;
import static cn.navigational.utils.Assert.isEmpty;
import static cn.navigational.utils.ResponseUtils.response;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.TokenUtils.generateKey;

public class RequireToken extends HttpValidator {
    private static Vertx vertx;

    private static JsonObject config;

    @Override
    public void handle(RoutingContext routingContext) {

        if (isSkip(config, routingContext)) {
            return;
        }

        HttpServerRequest request = routingContext.request();
        JsonObject jwtConfig = config.getJsonObject("jwtConfig");

        vertx.executeBlocking(fut -> {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (isEmpty(token)) {
                fut.fail("未检测到令牌");
                return;
            }
            int index = token.indexOf(" ");
            if (index <= 0) {
                fut.fail("令牌中未检测到Bearer");
                return;
            }
            String headStr = token.substring(0, index);
            if (!headStr.equals("Bearer")) {
                fut.fail("非法请求");
                return;
            }
            Key key = generateKey(jwtConfig.getString("key"));
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.substring(index + 1).trim())
                    .getBody();
            JsonObject principal = new JsonObject();
            claims.forEach(principal::put);
            JsonObject temp = routingContext.getBodyAsJson().put(USER, principal);
            routingContext.setBody(temp.toBuffer());
            fut.complete();
        }, rs -> {
            if (rs.failed()) {
                response(routingContext, responseFailed("请先登录", 403));
            } else {
                routingContext.next();
            }
        });
    }


    public static HttpValidator create(Vertx vertx, JsonObject config) {
        RequireToken.vertx = vertx;
        RequireToken.config = config;
        return new RequireToken();
    }
}
