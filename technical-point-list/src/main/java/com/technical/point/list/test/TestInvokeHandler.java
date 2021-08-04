package com.technical.point.list.test;

import com.technical.point.list.service.ITeacher;
import com.technical.point.list.service.impl.invoke.TeacherA;
import com.technical.point.list.service.impl.invoke.WorkHandler;

import java.lang.reflect.Proxy;

/**
 * @author: Mr.Gao
 * @date: 2021/8/4 21:05
 * @description:
 */
public class TestInvokeHandler {
    public static void main(String[] args) {
        //要代理的真实对象
        TeacherA teacherA = new TeacherA();
        //代理对象的调用处理程序，将真实对象传入代理对象的构造函数中
        final WorkHandler handler = new WorkHandler(teacherA);
        /**
         * 使用handler对象的classloader对象来加载我们的代理对象
         * 为代理类提供的接口是真实对象实现的接口
         * 我们将代理对象关联到上面的InvocationHandler对象
         */
        ITeacher proxyTeacher = (ITeacher) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{ITeacher.class}, handler);
        /**
         * 经过验证得知:在实现InvocationHandler接口的情况下，代理类执行任何方法时都会执行invoke方法。
         */
        proxyTeacher.study();
        proxyTeacher.work();
    }
}
