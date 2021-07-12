package com.gaogba.thread.mult;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/7/4 17:32
 * @description:
 */
public class MultThreadException {

    public int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + "start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "count=" + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {//此处抛出异常时，锁即被释放掉,要想不被释放掉，可进行catch处理，循环继续
                int i = 1 / 0;
                System.out.println(i);
            }

        }
    }

    public static void main(String[] args) {
        MultThreadException m = new MultThreadException();
        //线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.m();
            }
        }, "t1").start();

        //线程2
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.m();
            }
        }, "t2").start();

    }

}
