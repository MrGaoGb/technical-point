package com.technical.point.list.test.testthread;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 14:15
 * @description: <p>
 * sleep()方法：线程睡眠
 * 用来模拟网络延时,可以放大问题的发生性
 * <p>
 * - 线程睡眠
 * 1、sleep时间到达之后线程会进入就绪状态
 * 2、sleep可以模拟网络延时，倒计时等
 * 3、每一个对象都有一个锁，sleep不会释放锁(*****)
 * </p>
 */
public class TestSleep implements Runnable {

    private Integer ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //模拟网络延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "票!");
        }
    }


    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();
        new Thread(testSleep, "小明").start();
        new Thread(testSleep, "秦老师").start();
        new Thread(testSleep, "小宝").start();
        new Thread(testSleep, "黄牛党").start();
    }
}
