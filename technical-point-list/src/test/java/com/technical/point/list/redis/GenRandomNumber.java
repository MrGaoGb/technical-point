package com.technical.point.list.redis;

import java.util.Random;

/**
 * @author: Mr.Gao
 * @date: 2022年02月22日 10:08
 * @description:
 */
public class GenRandomNumber {
    public static void main(String[] args) {
        int number = new Random().nextInt(10) + 1;
        System.out.println(number);
    }
}
