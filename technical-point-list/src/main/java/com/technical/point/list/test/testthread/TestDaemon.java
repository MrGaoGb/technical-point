package com.technical.point.list.test.testthread;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 17:29
 * @description:测试守护线程 线程：分为用户线程和守护线程
 * <p>
 * 1、虚拟机必须确保用户线程执行完毕(main or async -> 用户线程)
 * 2、虚拟机不必等待守护线程执行完毕(gc -> 垃圾回收线程)
 * 3、后台记录操作日志，监控内存、垃圾回收机制等
 * </p>
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        Thread thread = new Thread(god);

        //默认为false,即就是用户线程，正常的线程都是用户线程
        //true 将god线程更改为守护线程
        thread.setDaemon(true);
        //启动守护线程
        thread.start();

        //启动用户线程
        new Thread(new People()).start();

    }
}


class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝一直守护着你");
        }
    }
}

class People implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("我开心快乐每一天!");
        }
        System.out.println("=======sayGoodBye world! =======");
    }
}