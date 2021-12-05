package com.technical.point.list.test.testthread.sync;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 18:18
 * @description: <p>
 * 线程不安全买票
 * </p>
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "苦逼的我").start();
        new Thread(buyTicket, "三秒的你们").start();
        new Thread(buyTicket, "可恶的黄牛党").start();
        new Thread(buyTicket, "打工上班族").start();
    }
}

class BuyTicket implements Runnable {

    private Integer ticketNums = 10;

    private boolean flag = true;

    @Override
    public synchronized void run() {
        while (flag) {
            if (ticketNums <= 0) {
                flag = false;
                break;
            }
            //模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 拿到第:" + ticketNums-- + "票!");
        }
    }
}
