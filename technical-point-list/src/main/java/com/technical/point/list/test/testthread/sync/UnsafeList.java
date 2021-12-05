package com.technical.point.list.test.testthread.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.Gao
 * @date: 2021/12/1 11:15
 * @description: <p>
 * 线程不安全的集合list
 * </p>
 * <p>
 * synchronized包含同步方法和同步代码块，此处锁的是变化的对象(增删改)
 * </p>
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                //同步锁 实现线程安全
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
               // list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
