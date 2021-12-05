package com.technical.point.list.test.testthread.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/5 17:56
 * @description: 生产者和消费者问题
 * <p>
 * 通过lock锁来实现
 * </p>
 */
public class TestPcByLock {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "D").start();
    }
}

/**
 * 同一资源类
 * 资源类(三部曲) > 判断等待 业务 通知
 */
class Data2 {

    //数字 资源类
    private Integer number = 0;
    /**
     * 此处使用lock锁的方式实现
     * condition:被用来代替Object对象中的wait()、notify()
     * ====> condition的优势:可以精准实现通知唤醒
     */
    private Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    /**
     * +1操作
     */
    public void increment() {
        //加锁
        lock.lock();
        try {
            /**
             * 为避免线程虚假唤醒 此处必须使用while() ,
             * ** >>>>>>>> 使用if会存在线程被虚假唤醒
             */
            while (number != 0) {
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ">>>" + number);
            //通知其他线程消费
            condition.signalAll();
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * -1操作
     */
    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ">>>" + number);
            //通知其他线程
            condition.signalAll();
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}