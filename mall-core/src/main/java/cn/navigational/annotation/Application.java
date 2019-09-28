package cn.navigational.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Vert.x启动注解
 *
 * @author YangKui
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Application {

    /**
     * 配置文件路径,多个配置文件以{@link String[]}数组的形式传递
     * 注意多个配置文件中若存在相同的key,后者会覆盖前者
     *
     * @return 返回配置文件路径数组
     */
    String[] configs() default "";
}
