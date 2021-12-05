package com.technical.point.list.test.testthread;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 16:15
 * @description:
 */
public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("son thread is running!!!");
        });

        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);

        //启动线程之后再次观察状态
        thread.start();
        state = thread.getState();
        System.out.println(state);

        //只要状态非终止状态 就一直观察线程状态
        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            state = thread.getState();
            System.out.println("running : " + state);
        }

    }
}
