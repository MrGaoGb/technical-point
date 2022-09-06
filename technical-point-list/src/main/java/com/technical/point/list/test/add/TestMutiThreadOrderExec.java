package com.technical.point.list.test.add;

import java.util.concurrent.*;

/**
 * @author: Mr.Gao
 * @date: 2022年08月03日 18:09
 * @description:控制线程T1、T2、T3 按顺序执行
 */
public class TestMutiThreadOrderExec {
    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    static CountDownLatch countDownLatch2 = new CountDownLatch(1);


    static Semaphore semaphore1 = new Semaphore(0);
    static Semaphore semaphore2 = new Semaphore(0);

    public static void main(String[] args) {
        // --采用CountDownLatch方式
         //testCountDownLatch();
        // --采用线程池方式
         testPool();

        System.out.println("-------------------以下两种执行顺序不能保证有序执行!");

        // --采用CyclicBarrier方式 无效
        // testCyclicBarrier();

        //使用信号量Semaphore
        //testSemaphore();
    }

    /**
     * 采用CountDownLatch方式
     */
    public static void testCountDownLatch() {
        Thread thread1 = new Thread(() -> {
            System.out.println("T1 开始执行!");
            countDownLatch1.countDown(); //-1
            System.out.println("T1 执行完毕!");
        });
        Thread thread2 = new Thread(() -> {
            try {
                countDownLatch1.await();//阻塞
                System.out.println("T2 开始执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 执行完毕!");
            countDownLatch2.countDown();
        });
        Thread thread3 = new Thread(() -> {
            try {
                countDownLatch2.await();//阻塞
                System.out.println("T3 开始执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T3 执行完毕!");
        });

        // 开启线程T1、T2、T3
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 采用线程池方式 线程池个数为1
     */
    public static void testPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread thread1 = new Thread(() -> {
            System.out.println("T1 开始执行!");
            System.out.println("T1 执行完毕!");
        });
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("T2 开始执行!");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 执行完毕!");
        });
        Thread thread3 = new Thread(() -> {
            try {
                System.out.println("T3 开始执行!");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T3 执行完毕!");
        });

        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);

        // --关闭线程池
        executorService.shutdown();
    }

    /**
     * 允许一组线程全部等待彼此
     */
    public static void testCyclicBarrier() {
        Thread thread1 = new Thread(() -> {
            try {
                cyclicBarrier1.await();//阻塞
                System.out.println("T1 开始执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("T1 执行完毕!");
        });
        Thread thread2 = new Thread(() -> {
            try {
                cyclicBarrier1.await();
                System.out.println("T2 开始执行!");
                cyclicBarrier2.await();//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("T2 执行完毕!");
        });
        Thread thread3 = new Thread(() -> {
            try {
                cyclicBarrier2.await();//阻塞
                System.out.println("T3 开始执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("T3 执行完毕!");
        });

        // 开启线程T1、T2、T3
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 使用信号量Semaphore
     */
    public static void testSemaphore() {
        Thread thread1 = new Thread(() -> {
            semaphore1.release();
            System.out.println("T1 开始执行!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 执行完毕!");
        });
        Thread thread2 = new Thread(() -> {
            try {
                // 阻塞
                semaphore1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore2.release();
            System.out.println("T2 开始执行!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 执行完毕!");
        });
        Thread thread3 = new Thread(() -> {
            try {
                // 阻塞
                semaphore2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T3 开始执行!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T3 执行完毕!");
        });

        // 开启线程T1、T2、T3
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
