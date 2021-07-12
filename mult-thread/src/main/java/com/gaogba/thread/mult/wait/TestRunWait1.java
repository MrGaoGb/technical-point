package com.gaogba.thread.mult.wait;

/**
 * @author: Mr.Gao
 * @date: 2021/7/5 09:17
 * @description:
 */
public class TestRunWait1 {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        threadB.setName("T-BBB");

        ThreadA threadA = new ThreadA(threadB);
        threadA.setName("T-AAA");
        threadA.start();
        threadB.start();

        /**
         * 情况1:(表示线程B在线程A进入等待状态之前没有死亡,且在线程B执行完之后,通过线程A的调用wait使得线程A停滞了一会,然后再打印wait之后:false以及T-AAA end 1625476711268信息,
         *          疑问是线程B在进行wait方法之后,是怎么被自主唤醒的? 猜测是线程A中的锁对象threadB在执行wait方法之后,threadB自主调用了notifyAll方法)
         * T-BBB beg 1625476711267
         * T-BBB end 1625476711267
         * T-AAA beg 1625476711267
         * wait之前：true
         * wait之后：false
         * T-AAA end 1625476711268
         */

        /**
         * 情况2:
         * T-AAA beg 1625476756026
         * wait之前：true
         * T-BBB beg 1625476756026
         * T-BBB end 1625476756026
         * wait之后：false
         * T-AAA end 1625476756026
         */

        /**
         * 情况3:(表示线程B在线程A进入等待状态之前就已经死亡,那么线程A会一直处于执行状态)
         * T-BBB beg 1625478075169
         * T-BBB end 1625478075169
         * T-AAA beg 1625478075169
         * wait之前：false
         */

    }
}
