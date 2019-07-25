package cn.navigational.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
