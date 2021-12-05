package com.technical.point.list.test.testthread.juc.pc;

/**
 * @author: Mr.Gao
 * @date: 2021/12/5 17:07
 * @description: 生产者消费者问题(线程协作通信问题)；
 * ====传统方式实现
 * 描述:
 * <p>
 * 多个线程同时操作同一资源，进行相互协作通信.
 * 线程A +1 通知 线程B -1
 * 线程B -1 通知 线程A +1
 * </p>
 * <p>
 * 问题:线程也可以唤醒，而不会被通知，中断或超时，即所谓的虚假唤醒 。 虽然这在实践中很少会发生，但应用程序必须通过测试应该使线程被唤醒的条件来防范，并且如果条件不满足则继续等待。 换句话说，等待应该总是出现在循环中，就像这样：
 * synchronized (obj) {
 * while (<condition does not hold>)
 * obj.wait(timeout);
 * ... // Perform action appropriate to condition
 * }
 * <p>
 * 官方文档 给出解决方案 -> 将if判断改成while即可
 * </p>
 */
public class TestPcBySyn {
    public static void main(String[] args) {
        Data data = new Data();
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
class Data {

    //数字 资源类
    private Integer number = 0;

    /**
     * +1操作
     */
    public synchronized void increment() {
        /**
         * 为避免线程虚假唤醒 此处必须使用while() ,
         * ** >>>>>>>> 使用if会存在线程被虚假唤醒
         */
        while (number != 0) {
            //等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + ">>>" + number);
        //通知其他线程消费
        this.notifyAll();
    }

    /**
     * -1操作
     */
    public synchronized void decrement() {
        while (number == 0) {
            //等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + ">>>" + number);
        //通知其他线程
        this.notifyAll();
    }
}