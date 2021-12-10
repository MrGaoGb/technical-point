package com.technical.point.list.test.testthread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/10 09:44
 * @description: 公平锁与非公平锁
 */
public class FairLock {
    public static void main(String[] args) {
        //公平锁与非公平锁 默认非公平锁
        Lock lock = new ReentrantLock();

        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

/**
 * 可重入锁案例引用(Synchronized方式实现)
 * 即就是：
 * 1、在sms()方法中获取到锁之后,同样的也可以拿到call()方法中的锁。
 * 2、在模拟线程A执行sms方法采用模拟延时的方式前提下，同样的结果也是可以拿到call方法的锁。(必须是等线程A释放call方法的锁之后，线程B才可以执行)
 */
class Phone {

    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " sms");
        try {
            //模拟下延时
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        call();//可重入锁案例引用
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " call");
    }

}

/**
 * 可重入锁案例引用(Lock方式实现)
 * 即就是：
 * 1、在sms()方法中获取到锁之后,同样的也可以拿到call()方法中的锁。
 * 2、在模拟线程A执行sms方法采用模拟延时的方式前提下，同样的结果也是可以拿到call方法的锁。(必须是等线程A释放call方法的锁之后，线程B才可以执行)
 */
class Phone2 {

    private Lock lock = new ReentrantLock();

    public void sms() {
        /**
         * 区别是
         * 1、lock使用的是两把不同的锁,而synchronized使用的是同一把锁对象。
         * 2、lock锁是配对使用的。 不配对的情况下会造成死锁问题
         */
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sms");
            try {
                //模拟下延时
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            call();//可重入锁案例引用
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//必须释放锁
        }

    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//必须释放锁
        }
    }

}