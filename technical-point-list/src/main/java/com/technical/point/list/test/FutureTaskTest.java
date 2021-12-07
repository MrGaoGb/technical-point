package com.technical.point.list.test;

import java.util.concurrent.*;

/**
 * @author: Mr.Gao
 * @date: 2021/12/7 09:22
 * @description: 测试FutureTask
 * <p>
 * 1、验证构造方法，public FutureTask(Runnable runnable,V result)
 * 2、FutureTask如果计算尚未完成，则get方法将阻止
 * </p>
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 验证Runnable接口同时夹带返回值
         */
        FutureTask<Boolean> futureTask = new FutureTask<>(() -> {
            System.out.println("futureTask 需要业务逻辑执行..");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, true);
        new Thread(futureTask).start();
        System.out.println("Runnable接口 " + futureTask.get());

        /**
         * 验证启动Callable接口
         */
        FutureTask<Boolean> futureTaskA = new FutureTask<>(() -> {
            //模拟业务逻辑处理
            System.out.println("futureTaskA 需要业务逻辑执行..");
            TimeUnit.SECONDS.sleep(3);
            return true;
        });
        //System.out.println("Callable接口 " + futureTaskA.get());
        new Thread(futureTaskA).start();
        System.out.println("Callable接口 " + futureTaskA.get());
        //主线程模拟延时
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(futureTask.get());

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Future<Boolean> future = executorService.submit(() -> {
            //模拟业务逻辑处理
            System.out.println("Executors future 需要业务逻辑执行..");
            TimeUnit.SECONDS.sleep(3);
            return true;
        });
        System.out.println(future.get());
        final Future<Boolean> future2 = executorService.submit(() -> {
            //模拟业务逻辑处理
            System.out.println("Executors future2 需要业务逻辑执行..");
            TimeUnit.SECONDS.sleep(5);
            return true;
        });
        System.out.println(future2.get());

        final Future<Boolean> future3 = executorService.submit(() -> {
            //模拟业务逻辑处理
            System.out.println("Executors future3 需要业务逻辑执行..");
            TimeUnit.SECONDS.sleep(1);
            return true;
        });
        System.out.println(future3.get());

        //关闭
        executorService.shutdown();

    }
}


