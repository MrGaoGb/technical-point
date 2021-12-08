package com.technical.point.list.test.volatile_t;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Mr.Gao
 * @date: 2021/12/8 20:45
 * @description: volatile不保证原子性
 */
public class Demo {

    //volatile不能保证原子性
    //private volatile static int num = 0;
    private static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //num++;//不是一个原子性操作
        num.getAndIncrement();
    }


    public static void main(String[] args) {

        //理论上 num在20个线程执行完的情况下 值为20000
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            //理论上 有main线程和gc线程存活(至少两个存活)
            Thread.yield();
        }
        System.out.println(num);
    }
}
