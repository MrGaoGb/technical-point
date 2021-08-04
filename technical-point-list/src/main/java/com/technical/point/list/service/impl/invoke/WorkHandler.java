package com.technical.point.list.service.impl.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: Mr.Gao
 * @date: 2021/8/4 21:00
 * @description:
 */
public class WorkHandler implements InvocationHandler {

    //代理类的真实对象
    private Object realObj;

    //构造函数 给代理对象赋值
    public WorkHandler(Object realObj) {
        this.realObj = realObj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println("啦啦啦 前置增强校验   执行方法名:" + methodName);
        return method.invoke(realObj, args);
    }
}
