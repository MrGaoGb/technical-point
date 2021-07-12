package com.gaogba.thread.cas;

/**
 * @author: Mr.Gao
 * @date: 2021/7/1 22:02
 * @description:
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("aaa");
        System.out.println(threadLocal.get());
        //threadLocal.set("被覆盖了啦啦啦bbb");
        System.out.println(threadLocal.get());
    }
}
