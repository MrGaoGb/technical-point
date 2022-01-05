package com.technical.point.list.jvm.stack;

import java.util.Random;

/**
 * @author: Mr.Gao
 * @date: 2022年01月05日 20:42
 * @description:
 */
public class HeapTest {

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * 演示：堆内存溢出
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "Heap OutOfMemoryError";
        while (true) {
            str += str + new Random().nextInt(8888) + new Random().nextInt(23672637);
        }
    }
}
