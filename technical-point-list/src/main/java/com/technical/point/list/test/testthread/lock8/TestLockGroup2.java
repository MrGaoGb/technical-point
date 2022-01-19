package com.technical.point.list.test.testthread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年01月19日 10:16
 * @description: 8锁问题
 * <p>
 * ==：8锁问题
 * 问题3:新增一个普通方法hello()，两个线程是先打印发短信还是hello ?  ==先hello后发短信
 * 问题4:新增两个对象（phone1、phone2） ，两个线程是先打印发短信还是先打电话 ? 先打电话后发短信
 * <p>
 */
public class TestLockGroup2 {
    public static void main(String[] args) {
        //锁的是不同的对象
        final Phone2 phone1 = new Phone2();
        final Phone2 phone2 = new Phone2();

        //原因是因为有锁的存在
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        //延时1s
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();


    }
}


class Phone2 {

    /**
     * 1、synchronized锁的对象是方法的调用者!!!
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

    /**
     * 普通方法,没有锁，不是同步方法，不受锁的影响!!!
     */
    public void hello() {
        System.out.println("hello");
    }
}