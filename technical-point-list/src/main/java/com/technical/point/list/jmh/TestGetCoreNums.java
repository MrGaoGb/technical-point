package com.technical.point.list.jmh;

/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 19:12
 * @description:
 */
public class TestGetCoreNums {
    public static void main(String[] args) {
        //8个线程
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
