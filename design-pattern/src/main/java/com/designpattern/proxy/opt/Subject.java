package com.designpattern.proxy.opt;

/**
 * @author: Mr.Gao
 * @date: 2021/6/15 17:39
 * @description:需要动态代理的接口
 */
public interface Subject {

    /**
     * 你好
     *
     * @param name
     * @return
     */
    String SayHello(String name);

    /**
     * 再见
     *
     * @return
     */
    String SayGoodBye();

}
