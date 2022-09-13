package com.technical.point.list.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年09月06日 9:43
 * @description:线程不断休眠 验证线程的TIMED_WARTING状态
 */
public class WaitingTime implements Runnable {


    @Override
    public void run() {
        while (true) {
            waitSecond(200);
        }
    }

    /**
     * 休眠N秒
     *
     * @param seconds
     */
    public static final void waitSecond(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
