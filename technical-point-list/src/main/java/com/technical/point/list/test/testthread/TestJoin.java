package com.technical.point.list.test.testthread;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 15:03
 * @description: join() ->> 让线程强制插入执行
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程VIP 编号: " + i + "前来!");
        }
    }

    public static void main(String[] args) {
        //启动VIP线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //Main开始执行
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                try {
                    thread.join();//让线程强制插入执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("普通线程 编号: " + i + "前来报道!");
        }
    }
}
