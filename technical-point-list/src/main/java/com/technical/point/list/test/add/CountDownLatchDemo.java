package com.technical.point.list.test.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 19:34
 * @description: 类似于计数器
 * <p>
 * 减法计数器
 * =========
 * 执行场景描述：
 * 总共6个线程执行，在一个线程执行完之后调用 countDownLatch.countDown(); 数量 -1，直到6个线程同时执行完之后 ，
 * 再继续向下执行 "Close door" 操作.
 * </p>
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 执行场景描述：
         * 总共6个线程执行，在一个线程执行完之后调用 countDownLatch.countDown(); 数量 -1，直到6个线程同时执行完之后 ，
         * 再继续向下执行 "Close door" 操作.
         */
        //6个线程倒计时
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go Out classRoom!");
                countDownLatch.countDown(); //表示数量 -1
            }, String.valueOf(i))
                    .start();
        }

        countDownLatch.await();//等待计数器归0 然后再向下执行
        //每次有线程调用countDownLatch.countDown() 数量-1，当计数值为0时，countDownLatch.await()会被唤醒。继续执行!
        System.out.println("Close door!");
    }
}
