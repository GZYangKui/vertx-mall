package cn.navigational.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注当前类为verticle,用于启动应用扫描部署verticle
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Verticle {
    /**
     * 当前verticle对象描述
     */
    String description() default "";

    /**
     * base api路径
     *
     * @return
     */
    String baseApi() default "";
}
