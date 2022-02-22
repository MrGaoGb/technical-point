package com.technical.point.list.test.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/12/8 17:40
 * @description: Future(异步回调类)
 * <p>
 * 实现类如下:
 * FutureTask:
 * CompletableFuture:(此处使用这个)
 * ForkJoinTask:
 * </p>
 */
public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //异步回调处理 runAsync 没有返回值 ForkJoinPool.commonPool-worker-1 执行完了
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " 执行完了");
//        });
//        System.out.println("继续执行其他业务");
//        completableFuture.get();//获取阻塞执行结果

        //异步回调处理 supplyAsync 带返回值
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer temp = 10 / 0;//模拟异常情况

            System.out.println(Thread.currentThread().getName() + " 执行完了");
            return 10248;
        });
        System.out.println("继续执行其他业务");
        final CompletableFuture<Integer> exceptionally = completableFuture.whenComplete((t, u) -> {
            //当线程正常执行时
            System.out.println("t=" + t);
            System.out.println("u=" + u);
        }).exceptionally((e) -> {
            //当线程执行异常时 会拿到错误异常信息
            System.out.println("执行异常时" + e.getMessage() + " 异常状态码: 11111");
            return null;
        });
        System.out.println(CompletableFuture.anyOf(exceptionally).get());
        /**
         * 有返回值的CompletableFuture
         * >>>>>>>　ForkJoinPool.commonPool-worker-1 执行完了
         * >>>>>>>　t=10248
         *　>>>>>>>　 u=null
         * >>>>>>>　执行结果: 10248
         */
    }
}
