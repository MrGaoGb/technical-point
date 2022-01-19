package com.technical.point.list.test.testthread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年01月19日 10:16
 * @description: 8锁问题
 * <p>
 * ==：8锁问题
 * 问题1:标准线程下，两个线程是先打印发短信还是先打电话 ?  ==先发短信后打电话
 * 问题2:sendSms延时4s，两个线程是先打印发短信还是先打电话 ? 先发短信后打电话
 * <p>
 * 问题原因：
 * 1、有锁的存在
 * 2、synchronized锁的是方法的调用者
 */
public class TestLockGroup1 {
    public static void main(String[] args) {
        final Phone phone = new Phone();

        //原因是因为有锁的存在
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        //延时1s
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();


    }
}


class Phone {

    /**
     * 1、synchronized锁的对象是方法的调用者!!!
     * 2、两个方法的锁是同一个对象，谁先拿到谁先执行!!!
     */
    public synchronized void sendSms() {
        //延时4s
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信!");
    }

    public synchronized void call() {
        System.out.println("打电话!");
    }
}