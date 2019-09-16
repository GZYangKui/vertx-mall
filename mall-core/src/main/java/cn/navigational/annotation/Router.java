package cn.navigational.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解用于标注在RouterVerticle上。
 * 一般该注解与RequestMapping组合使用
 *
 * @author GZYangKui
 * @see RouterMapping
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Router {
    /**
     * api
     *
     * @return
     */
    String api() default "";

    /**
     * 接口描述
     *
     * @return
     */
    String description() default "";
}
