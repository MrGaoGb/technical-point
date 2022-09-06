package com.technical.point.list.test.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 20:02
 * @description: 信号量值
 * <p>
 * 多个共享资源互斥的使用 | 应用于并发限流 控制最大的线程数
 * </p>
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //抢车位 3个线程
        Semaphore semaphore = new Semaphore(1);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//得到 假设队列如果满了，其他线程就回进入阻塞
                    System.out.println(Thread.currentThread().getName() + " 抢到车位!");
                    TimeUnit.SECONDS.sleep(2);//沉睡两秒
                    System.out.println(Thread.currentThread().getName() + " 离开车位!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); //当前线程 释放 然后唤醒等待的线程!
                }
            }, String.valueOf(i)).start();
        }
    }
}
