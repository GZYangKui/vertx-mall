package cn.navigational.auth;

import cn.navigational.base.HttpValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;


import java.security.Key;

import static cn.navigational.config.Constants.SKIP;
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
        final HttpServerRequest request = routingContext.request();

        final String uri = request.uri();

        final JsonArray skips = config.getJsonArray(SKIP);

        final JsonObject jwtConfig = config.getJsonObject("jwtConfig");

        //如果包含在不授权列表中 则直接跳过验证
        if (skips.contains(uri)) {
            routingContext.next();
            return;
        }

        vertx.executeBlocking(_fut -> {
            final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (isEmpty(token)) {
                _fut.fail("未检测到令牌");
                return;
            }
            final int index = token.indexOf(" ");
            if (index <= 0) {
                _fut.fail("令牌中未检测到Bearer");
                return;
            }
            final String headStr = token.substring(0, index);
            if (!headStr.equals("Bearer")) {
                _fut.fail("非法请求");
                return;
            }
            final Key key = generateKey(jwtConfig.getString("key"));
            final Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.substring(index + 1).trim())
                    .getBody();
            final JsonObject principal = new JsonObject();
            claims.forEach(principal::put);
            final JsonObject temp = routingContext.getBodyAsJson().put(USER, principal);
            routingContext.setBody(temp.toBuffer());
            _fut.complete();
        }, _rs -> {
            if (_rs.failed()) {
                _rs.cause().printStackTrace();
                final JsonObject msg = responseFailed("请先登录", 403);
                response(routingContext, msg);
            } else {
                routingContext.next();
            }
        });
    }


    public static HttpValidator create(final Vertx vertx, JsonObject config) {
        RequireToken.vertx = vertx;
        RequireToken.config = config;
        return new RequireToken();
    }
}
