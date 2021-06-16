package com.designpattern.proxy.opt;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Mr.Gao
 * @date: 2021/6/15 17:41
 * @description:
 */
public class DynamicProxyDemonstration {

    public static void main(String[] args) {

        //代理的真实对象
        Subject subject = new RealSubject();
        /**
         * ClassLoader loader:指的是当前目标对象的类加载器
         * Class<?>[] interfaces:目标对象实现的接口类型
         * InvocationHandler h:执行目标方法时会被触发
         * return:Object ->com.designpattern.proxy.opt.RealSubject@5f5a92bb
         */
        final Subject proxyInstanceSubject = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //在代理真实对象前我们可以添加一些自己的操作
                System.out.println("在调用之前，我要干点啥呢？");

                System.out.println("Method:" + method);

                //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
                Object returnValue = method.invoke(subject, args);

                //在代理真实对象后我们也可以添加一些自己的操作
                System.out.println("在调用之后，我要干点啥呢？");
                return returnValue;
            }
        });
        //代理类:proxyInstanceSubject com.sun.proxy.$Proxy0
        System.out.println(proxyInstanceSubject.getClass().getName());
        String hello = proxyInstanceSubject.SayHello("Mr.Gao king");
        System.out.println(hello);
        //将生成的字节码保存到本地
        createProxyClassFile();
    }

    private static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Subject.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            System.out.println((new File("hello")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
