package com.technical.point.list.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Mr.Gao
 * @date: 2021/8/13 17:35
 * @description:
 */
public class TestSleepMethod {

    private static final List<String> charList = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    public static void main(String[] args) {
        Thread threadNum = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);//1秒输出一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadLetter = new Thread(() -> {
            for (int i = 0; i < charList.size(); i++) {
                System.out.println(charList.get(i));
                try {
                    Thread.sleep(1000);//1秒输出一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Thread.sleep(5000);
            System.out.println("Main 线程先沉睡5s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5s 过后开始抢CPU来执行");

        //启动线程
        threadNum.start();
        threadLetter.start();

    }
}
