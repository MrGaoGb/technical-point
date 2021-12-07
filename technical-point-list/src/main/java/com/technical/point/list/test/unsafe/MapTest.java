package com.technical.point.list.test.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Mr.Gao
 * @date: 2021/12/6 20:19
 * @description: Map集合
 * <p>
 * Map集合
 * 报错:ConcurrentModificationException
 * </p>
 */
public class MapTest {
    public static void main(String[] args) {
        /**
         * 1、Map<String,Object> map = new HashMap<>() //线程不安全，类比于HashSets
         * 2、Map<String, Object> map = Collections.synchronizedMap(new HashMap<>()); //线程安全
         * 3、Map<String, Object> map = new ConcurrentHashMap<>();//线程安全
         */
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
