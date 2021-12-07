package com.technical.point.list.test.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 18:29
 * @description: 并发异常：ConcurrentModificationException
 * 同理论证: Set | Map
 */
public class ListTest {
    public static void main(String[] args) {
        //List 线程不安全的 ArrayList 线程不安全的
        /**
         * 使用线程安全的解决方案:
         * 1、List<String> list = new Vector<>(); // 底层使用synchronized
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>()); //工具类生成同步集合
         * 3、List<String> list = new CopyOnWriteArrayList<>(); //底层使用lock锁 且写入时复制
         *
         * CopyOnWriteArrayList ->> 写入时复制，COW，属于计算机程序设计领域的一种优化策略
         * 在写入的时候避免覆盖，造成数据问题(所以CopyOnWriteArrayList采用写入时复制)
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 10));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //System.out.println(list);
    }
}
