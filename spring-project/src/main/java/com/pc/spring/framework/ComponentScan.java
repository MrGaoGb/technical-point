package com.pc.spring.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 扫描类注解
 * @author: Mr.Gao
 * @create: 2021-05-11 22:18
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
    /**
     * 指定扫描包路径
     *
     * @return
     */
    String value() default "";
}
