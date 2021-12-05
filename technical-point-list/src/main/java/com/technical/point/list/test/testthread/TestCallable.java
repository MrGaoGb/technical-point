package com.technical.point.list.test.testthread;

import java.util.concurrent.*;

/**
 * @author: Mr.Gao
 * @date: 2021/12/2 18:22
 * @description:
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<String> submit = executorService.submit(new DemoCallable());
        System.out.println("有值没");
        //异步执行获取返回值
        System.out.println(submit.get());
        System.out.println("不清楚");
        //最后关闭线程池
        executorService.shutdown();
    }
}


class DemoCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        //模拟延时...
        Thread.sleep(1000);
        return "bo bo 你快来!";
    }
}
