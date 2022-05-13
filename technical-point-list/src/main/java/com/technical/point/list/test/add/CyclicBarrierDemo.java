package com.technical.point.list.test.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 19:48
 * @description: 集齐7颗龙珠之后召唤神龙
 * <p>
 * 加法计数器
 * </p>
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("7颗龙珠集齐召唤神龙!");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "集齐第 " + temp + " 颗龙珠!");
                try {
                    cyclicBarrier.await();//表示其中一个线程执行完毕之后 进入等待...
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
