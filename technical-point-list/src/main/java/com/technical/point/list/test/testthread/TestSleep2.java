package com.technical.point.list.test.testthread;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 14:26
 * @description:用来模拟倒计时
 */
public class TestSleep2 {

    public static void main(String[] args) {
        //tenDown();

        printCurrDate();
    }

    /**
     * 模拟倒计时
     *
     * @throws InterruptedException
     */
    public static void tenDown() {
        Integer num = 10;
        while (true) {
            if (num <= 0) {
                break;
            }
            //模拟网络延时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("倒计时 " + (Objects.equals(10, num) ? "开始" : "") + ": " + num--);
        }
    }

    /**
     * 打印系统当前时间
     */
    public static void printCurrDate() {
        LocalDateTime now = LocalDateTime.now();
        while (true) {
            String currTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("当前时间 时间格式(HH:mm:ss) :" + currTime);
            //模拟延时1s
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            now = LocalDateTime.now();
        }
    }
}
