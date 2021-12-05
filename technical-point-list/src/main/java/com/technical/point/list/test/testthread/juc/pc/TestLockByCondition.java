package com.technical.point.list.test.testthread.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/5 18:20
 * @description: condition实现精准通知唤醒
 * <p>
 * 场景描述:有三个线程执行，但是执行的顺序是 线程A -> 线程B -> 线程C，按照顺序依次执行
 * </p>
 */
public class TestLockByCondition {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();

    }
}

/**
 * 资源类(三部曲):判断等待、业务 、唤醒
 */
class Data3 {

    private Lock lock = new ReentrantLock();
    final Condition conditionA = lock.newCondition();
    final Condition conditionB = lock.newCondition();
    final Condition conditionC = lock.newCondition();
    // 根据标识位 来确认具体线程 1-A 2-B 3-C
    private Integer number = 1;

    public void printA() {
        //加锁
        lock.lock();
        try {
            while (number != 1) {
                //等待
                conditionA.await();
            }
            //业务逻辑
            number = 2;
            System.out.println(Thread.currentThread().getName() + ">>>>>>= AAAAAAAAA");
            //唤醒 B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    public void printB() {
        //加锁
        lock.lock();
        try {
            while (number != 2) {
                //等待
                conditionB.await();
            }
            //业务逻辑
            number = 3;
            System.out.println(Thread.currentThread().getName() + ">>>>>>= BBBBBBB");
            //B执行完之后 唤醒C
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    public void printC() {
        //加锁
        lock.lock();
        try {
            while (number != 3) {
                //等待
                conditionC.await();
            }
            //业务逻辑
            number = 1;
            System.out.println(Thread.currentThread().getName() + ">>>>>>= CCCCCCC");
            //C执行完之后 唤醒A
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
