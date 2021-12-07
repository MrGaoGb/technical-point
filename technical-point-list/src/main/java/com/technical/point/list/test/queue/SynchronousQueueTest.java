package com.technical.point.list.test.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/12/7 17:55
 * @description: 同步队列
 * <p>
 * 同步队列:
 * SynchronousQueue:
 * 1、其中每个插入操作必须等待另一个线程相应的删除操作
 * 2、同步队列没有任何内部容量，甚至没有一个容量
 * 3、此类支持可选的公平策略，用于订购等待的生产者和消费者线程。 默认情况下，此订单不能保证。 然而，以公平设置为true
 * </p>
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        //一个线程存元素
        new Thread(() -> {
            //添加元素1
            try {
                System.out.println(Thread.currentThread().getName() + "=> put 1");
                synchronousQueue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //添加元素2
            try {
                System.out.println(Thread.currentThread().getName() + "=> put 2");
                synchronousQueue.put("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //添加元素3
            try {
                System.out.println(Thread.currentThread().getName() + "=> put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        //另外一个线程获取元素
        new Thread(() -> {
            //获取元素1
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取元素2
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取元素3
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
