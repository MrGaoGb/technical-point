package com.technical.point.list.test.pool;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/12/7 18:32
 * @description: 线程池的三大方法
 * <p>
 * //ExecutorService executorService=Executors.newSingleThreadExecutor();//创建单个线程池
 * //ExecutorService executorService = Executors.newFixedThreadPool(10);//创建一个固定的线程池大小
 * //ExecutorService executorService = Executors.newCachedThreadPool();//可伸缩的，遇强则强 遇弱则弱
 * </p>
 */
public class TestPoolThreeMethod {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//创建单个线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(10);//创建一个固定的线程池大小
        //ExecutorService executorService = Executors.newCachedThreadPool();//可伸缩的，遇强则强 遇弱则弱

        /**
         * 最大线程数该如何配置(线程数调优的两种方式)
         * 1、CPU 密集型 几核就是几 可以保持CPU的效率最高
         * 2、IO　密集型 (程序中有15个大型任务 IO非常消耗资源) 判断程序中十分耗费IO的线程数 ，一般情况下，只要大于十分耗费IO线程数就可以，最好是耗费IO线程数的2倍
         */

        //代码获取系统的核数(当前电脑的核数),返回 Java 虚拟机可用的处理器数
        //System.out.println(Runtime.getRuntime().availableProcessors());

        /**
         * //七大参数备注
         * //经典案例 银行柜台办理业务
         * public ThreadPoolExecutor(int corePoolSize, //核心线程数
         *                          int maximumPoolSize, //最大线程数
         *                          long keepAliveTime, //在空余线程的情况下(此时只让核心线程工作)，再存活keepAliveTime之后销毁
         *                          TimeUnit unit, //keepAliveTime对应的单位值,TimeUnit.SECONDS //秒
         *                          BlockingQueue<Runnable> workQueue,// 阻塞队列 LinkedBlockingDeque(3) 当阻塞队列队满时，会适当的增加线程数，直至增加到最大线程为止
         *                          ThreadFactory threadFactory,//默认的方式 创建线程池工厂类
         *                          RejectedExecutionHandler handler//四种拒绝策略 即 当阻塞队列容量 + maximumPoolSize = 最大承载线程数，在满足最大承载线程数的情况下。启用拒绝策略
         *                          ) {
         *         super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
         *         this.prestartAllCoreThreads();
         *     }
         *
         *     ========================四大拒绝策略========
         *     ThreadPoolExecutor.AbortPolicy(); //相当于超过了最大线程承载数之后 会抛出异常RejectedExecutionException
         *     ThreadPoolExecutor.CallerRunsPolicy();//哪来的去哪里 (此处 main线程处理 ====> main 操作ok!)
         *     ThreadPoolExecutor.DiscardPolicy(); //队列满了 不会抛出异常 会被丢掉
         *     ThreadPoolExecutor.DiscardOldestPolicy();//队列满了 会尝试和最早的线程竞争 也不会抛出异常
         */
        //手动创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            /**
             * 线程最大承载数；queue(3) + max(5) = 8
             */
            /**
             * 1、当i=2时，表示此时只有核心线程数在工作
             * 2、当i=5时，正常情况下，应该也只有核心线程数在工作，而我电脑是3个线程在工作
             * 3、当i=6时，除了核心线程数在工作之外 ，新增1个线程 总共3个线程在工作
             * 4、当i=7时，4个线程同时工作
             * 5、当i=8时，5个线程同时工作(此时满足最大线程数)
             * 6、当i=9时，相当于超过了最大承载数，此时会启用四大拒绝策略之一
             */
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " " + finalI + " 操作ok!");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池 必须手动关闭
            executorService.shutdown();
        }
    }
}
