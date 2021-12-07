package com.technical.point.list.test.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/7 14:18
 * @description: 读写锁
 * 读写锁(更加细粒度)
 * <p>
 * 独占锁 (写锁) : 一次只能被一个线程占有
 * 共享锁 (读锁) : 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 不能共存
 * </p>
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {

        //多线程情况下会存在 多个线程同时执行写操作 存在风险
        //MyCache myCache = new MyCache();

        MyCacheLock myCache = new MyCacheLock();//使用读写锁 解决上边风险
        //模拟多线程环境 6个线程同时操作写入数据
        for (int i = 1; i < 6; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }

        //模拟多线程环境 6个线程同时读入数据
        for (int i = 1; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 缓存类
 * 使用读写锁实现
 */
class MyCacheLock {

    //使用volatile保证多线程情况下 保证可见性
    private static volatile Map<String, Object> map = new HashMap<>();

    //默认 非公平锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 获取缓存数据
     *
     * @return
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 添加数据
     * <p>
     * 要求：在写入的时候只有一个线程在执行写入操作
     * </p>
     *
     * @param k
     * @param v
     */
    public void put(String k, Object v) {
        //获取到一个读锁
        readWriteLock.writeLock().lock();//枷锁
        try {
            System.out.println(Thread.currentThread().getName() + " 写入 " + k);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + " 写入OK " + k + "此时缓存数据为: " + map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();//解锁
        }
    }

    /**
     * 获取数据
     * <p>
     * 要求：在读的时候 多个线程可以同时请求获取数据
     * </p>
     *
     * @param k
     */
    public void get(String k) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读取 " + k);
            map.get(k);
            System.out.println(Thread.currentThread().getName() + " 读取OK ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}

/**
 * 缓存类
 * 缺点:
 * <p>
 * 1、在多线程的情况下，会出现 A线程写入的时候 B线程同时也在写入
 * <p>
 * 解决方法:使用synchronized(同步锁)、lock(juc包下)或者使用读写锁(粒度更加细化)
 * </p>
 * </p>
 */
class MyCache {

    //使用volatile保证多线程情况下 保证可见性
    private static volatile Map<String, Object> map = new HashMap<>();

    /**
     * 获取缓存数据
     *
     * @return
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 添加数据
     *
     * @param k
     * @param v
     */
    public void put(String k, Object v) {
        System.out.println(Thread.currentThread().getName() + " 写入 " + k);
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + " 写入OK " + k);
    }

    /**
     * 获取数据
     *
     * @param k
     */
    public void get(String k) {
        System.out.println(Thread.currentThread().getName() + " 读取 " + k);
        map.get(k);
        System.out.println(Thread.currentThread().getName() + " 读取OK ");
    }
}