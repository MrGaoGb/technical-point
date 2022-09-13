package com.technical.point.list.test.thread;

/**
 * @author: Mr.Gao
 * @date: 2022年09月06日 10:13
 * @description:线程排序-join方法
 */
public class ThreadSortByJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1----" + Thread.currentThread().getState());
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2----" + Thread.currentThread().getState());
        }, "thread2");
        Thread thread3 = new Thread(() -> {
            System.out.println("thread3----" + Thread.currentThread().getState());
        }, "thread3");
        System.out.println("thread1状态:" + thread1.getState() + ",是否存活着:" + thread1.isAlive());
        System.out.println("thread2状态:" + thread2.getState() + ",是否存活着:" + thread2.isAlive());
        System.out.println("thread3状态:" + thread3.getState() + ",是否存活着:" + thread3.isAlive());
        thread1.start();
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());
        thread1.join();// --等待thread1执行完
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());
        thread2.start();
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());
        thread2.join();// --等待thread2执行完
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());
        thread3.start();
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());
        thread3.join();// --等待thread3执行完
        System.out.println(thread2.getName() + "<<>>" + thread2.getState() + "<>" + thread1.getName() + "_" + thread1.getState() + "<>" + thread3.getName() + "_" + thread3.getState());

    }
}
