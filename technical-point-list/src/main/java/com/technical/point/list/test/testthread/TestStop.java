package com.technical.point.list.test.testthread;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 11:34
 * @description: 测试Stop:
 * <p>
 * 1、建议让线程自己停止 --> 利用次数，不建议用死循环
 * 2、建议使用标志位 --> 设置一个标志位
 * 3、不要使用destory()或JDK已经过时的方法
 * </p>
 */
public class TestStop implements Runnable {

    private Boolean flag;

    @Override
    public void run() {
        this.flag = true;
        while (flag) {
            System.out.println("线程执行中......");
        }
        System.out.println("子线程执行完成...");
    }

    //提供一个公开的停止方法,供外面调用
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("Main 主线程执行中...i=" + i);
            if (i == 188) {
                //让子线程停止
                testStop.stop();
                System.out.println("子线程已停止! 此时i=" + i);
            }
        }
    }
}
