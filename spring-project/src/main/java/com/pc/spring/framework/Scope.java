package com.pc.spring.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: Bean的作用域
 * @author: Mr.Gao
 * @create: 2021-05-11 23:31
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
    /**
     * 默认是单例 singleton 多例 prototype
     *
     * @return
     */
    String value();
}
