package com.technical.point.list.test.testthread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/2 10:30
 * @description: Lock锁
 * <p>
 * Lock:锁对象（是个接口）
 * ReentrantLock:可重入锁
 * </p>
 * <p>
 * synchronized和Lock锁的区别
 * 1、Lock是显示锁(需要手动开启和关闭锁)，synchronized是隐式锁(出了作用域自动释放)。
 * 2、Lock只有代码块锁，synchronized有代码块锁和方法锁。
 * 3、使用Lock锁，JVM将花费较少的时间来调度线程，性能更好，并且具有更好的扩展性(众多子类对象)。
 * </p>
 */
public class TestLock {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket).start();
        new Thread(buyTicket).start();
        new Thread(buyTicket).start();
        new Thread(buyTicket).start();
    }
}

class BuyTicket implements Runnable {

    Integer ticketNums = 10;

    /**
     * 创建一个可重入锁对象
     */
    final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("购买第:" + ticketNums-- + "票!");
                } else {
                    System.out.println("余票不足!");
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
