package com.technical.point.list.test.thread;

/**
 * @author: Mr.Gao
 * @date: 2022年09月06日 9:55
 * @description:
 */
public class BlockedThread implements Runnable {
    @Override
    public void run() {
        synchronized (this.getClass()) {
            while (true) {
                WaitingTime.waitSecond(100);
            }
        }
    }
}
