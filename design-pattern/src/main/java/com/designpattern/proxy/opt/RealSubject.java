package com.designpattern.proxy.opt;

/**
 * @author: Mr.Gao
 * @date: 2021/6/15 17:40
 * @description:需要被代理的对象
 */
public class RealSubject implements Subject {
    @Override
    public String SayHello(String name) {
        return "hello" + name;
    }

    @Override
    public String SayGoodBye() {
        return "goodBye";
    }
}
