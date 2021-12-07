package com.technical.point.list.test.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 20:13
 * @description: Set集合
 * <p>
 * Set集合
 * 报错:ConcurrentModificationException 并发修改异常
 * </p>
 */
public class SetTest {
    public static void main(String[] args) {
        /**
         * 1、Set<String> set = new HashSet<>() //线程不安全的 底层是HashMap
         * 2、Set<String> set = Collections.synchronizedSet(new HashSet<>());//线程安全
         * 3、Set<String> set = new CopyOnWriteArraySet<>();//线程安全
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
