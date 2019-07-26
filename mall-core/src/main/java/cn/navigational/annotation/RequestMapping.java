package cn.navigational.annotation;

import io.vertx.core.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    /**
     * 请求url
     *
     * @return
     */
    String api() default "";

    /**
     * 请求方法
     *
     * @return
     */
    HttpMethod method() default HttpMethod.GET;

    /***
     *
     *
     * 接口说明
     * @return
     */
    String description() default "";

}
