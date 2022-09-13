package com.technical.point.list.test.thread;

/**
 * @author: Mr.Gao
 * @date: 2022年09月06日 9:52
 * @description:
 */
public class WaitingState implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (this.getClass()) {
                try {
                    this.getClass().wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
