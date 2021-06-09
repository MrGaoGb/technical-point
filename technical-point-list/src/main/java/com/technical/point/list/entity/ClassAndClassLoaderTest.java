package com.technical.point.list.entity;

/**
 * @description: Class和ClassLoader不同加载方式
 * @author: Mr.Gao
 * @create: 2021-04-21 22:03
 **/
public class ClassAndClassLoaderTest {

    /**
     * 无参构造器
     */
    public ClassAndClassLoaderTest() {
        System.out.println("ClassAndClassLoaderTest 对象被实例化了....!");
    }

    static {
        System.out.println("执行静态代码块....!");
    }


    public static String staticMethod() {
        System.out.println("静态方法被执行了.....");
        return "静态方法已被执行";
    }

    private static String staticArgs = staticMethod();
}
