package com.technical.point.list.test.testthread.sync;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: Mr.Gao
 * @date: 2021/12/1 15:36
 * @description: 测试JUC包下的类对象
 */
public class TestSafeJucList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }

        //模拟延时
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
