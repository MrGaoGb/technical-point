package com.gaogba.thread.mult.wait;

/**
 * @author: Mr.Gao
 * @date: 2021/7/5 09:12
 * @description:
 */
public class ThreadA extends Thread {

    private ThreadB threadB;

    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    /**
     * ThreadA持有锁对象为ThreadB对象
     */
    @Override
    public void run() {
        synchronized (threadB) {
                    System.out.println(Thread.currentThread().getName() + " beg "
                            + System.currentTimeMillis());
                    try {
                        System.out.println("wait之前：" + threadB.isAlive());
                        threadB.wait();
                        System.out.println("wait之后：" + threadB.isAlive());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end "
                            + System.currentTimeMillis());
        }
    }
}
