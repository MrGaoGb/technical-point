package com.technical.comm.annotation;

import java.lang.annotation.*;

/**
 * @Description 功能描述:配合AOP切面来实现日志打印
 * @Author gaogba
 * @Date 2020/8/6 15:04
 * @Version 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ControllerLoggerInfo {

    /**
     * 用来标注接口名
     *
     * @return
     */
    String value() default "";

    /**
     * 用来标识是否需要分页日志信息,默认是不需要:false
     * 1.为true时 分别打印code:{},message:{},count:{}
     * 2.为false时，仅打印code:{},message:{}
     *
     * @return
     */
    boolean isNeedPageLoggerInfo() default false;

    /**
     * 用来标识是否需要打印所有信息包括code:{},message:{},data:{}，默认为false即就是不打印data
     * 为true表示需要打印
     *
     * @return
     */
    boolean isShowDataInfo() default false;

    /**
     * 用于接口中是否需要校验userSession的有效期，默认是true(需要)，
     * false(不需要验证)
     * @return
     */
    boolean isValidExpireTime() default true;
}
