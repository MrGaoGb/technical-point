package com.gaogba.thread.mult.wait;

/**
 * @author: Mr.Gao
 * @date: 2021/7/5 09:13
 * @description:
 */
public class ThreadB extends Thread {

    /**
     * ThreadB 持有锁对象为 this即当前ThreadB对象
     */
    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }
}
