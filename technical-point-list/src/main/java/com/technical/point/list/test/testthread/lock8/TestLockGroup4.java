package com.technical.point.list.test.testthread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年01月19日 10:16
 * @description: 8锁问题
 * <p>
 * ==：8锁问题
 * 问题7: 新增一个静态同步方法标识(static) 一个同步方法 一个对象 ，两个线程是先打印发短信还是先打电话 ?  ==先打电话后发短息（原因是锁的对象不同，线程A锁的是Phone4.class，而线程B锁的是对象phone）
 * 问题6:新增一个静态同步方法标识(static) 一个同步方法 两个对象  ，两个线程是先打印发短信还是先打电话 ? ==先打电话后发短信
 * <p>
 */
public class TestLockGroup4 {
    public static void main(String[] args) {
        //锁的是不同的对象
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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


class Phone4 {

    /**
     * 1、synchronized锁的对象是方法的调用者!!!
     * 2、static静态方法 类一加载就有了 且class模板唯一
     */
    public static synchronized void sendSms() {
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