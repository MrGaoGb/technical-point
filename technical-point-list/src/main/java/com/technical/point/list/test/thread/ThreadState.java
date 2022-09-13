package com.technical.point.list.test.thread;

/**
 * @author: Mr.Gao
 * @date: 2022年09月06日 9:56
 * @description:
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new WaitingTime(), "WaitingTimeThread").start();
        new Thread(new WaitingState(), "WaitingStateThread").start();

        // block-01 抢到 block-02
        new Thread(new BlockedThread(), "BlockedThread-01").start();
        new Thread(new BlockedThread(), "BlockedThread-02").start();
    }
}
