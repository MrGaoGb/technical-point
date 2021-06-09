package com.pc.spring.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 依赖注入标记注解
 * @author: Mr.Gao
 * @create: 2021-05-11 22:27
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    /**
     * 用来标记当前类的别名
     *
     * @return
     */
    String value() default "";
}
