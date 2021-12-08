package com.technical.point.list.test.fockjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author: Mr.Gao
 * @date: 2021/12/8 16:17
 * @description:ForkJoin ForkJoin
 * <p>
 * ForkJoin相当于大数据中的Map Reduce结构，
 * 主要特点：它可以将一个大的任务拆分成多个子任务并行处理，最后将各个子任务的结果合并成最后的计算结果，并进行输出
 *
 * </p>
 */
public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1();//按照普通计算的方式执行 num=2000000001000000000 ,耗时:9217ms
        //test2();//采用ForkJoin方式 num=2000000001000000000 ,耗时:8038ms
        test3();//采用Stream方式计算 num=2000000001000000000 ,耗时:286ms 前面两种都是DD
    }

    /**
     * 按照普通计算的方式执行
     */
    public static void test1() {
        final long begin = System.currentTimeMillis();
        Long num = 0L;
        for (Long index = 1L; index <= 20_0000_0000; index++) {
            num += index;
        }
        final long end = System.currentTimeMillis();
        System.out.println("test1 num=" + num + " ,耗时:" + (end - begin) + "ms");
    }

    /**
     * 采用ForkJoin方式
     * execute(ForkJoinTask<?> task) //执行子线程 无返回值
     * submit(ForkJoinTask<?> task) //执行子线程 有返回值
     */
    public static void test2() throws ExecutionException, InterruptedException {
        final long begin = System.currentTimeMillis();
        //类似于线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyForkJoinTask myForkJoinTask1 = new MyForkJoinTask(1L, 20_0000_0000L);
        final ForkJoinTask<Long> submit = forkJoinPool.submit(myForkJoinTask1);
        final Long num = submit.get();
        final long end = System.currentTimeMillis();
        System.out.println("test2 num=" + num + " ,耗时:" + (end - begin) + "ms");
    }

    /**
     * 采用Stream方式计算
     */
    public static void test3() {
        final long begin = System.currentTimeMillis();
        final long num = LongStream.rangeClosed(1L, 20_0000_0000L).parallel().reduce(0, Long::sum);
        final long end = System.currentTimeMillis();
        System.out.println("test3 num=" + num + " ,耗时:" + (end - begin) + "ms");
    }
}

/**
 * 使用ForkJoin
 */
class MyForkJoinTask extends RecursiveTask<Long> {

    //阈值
    private Long thresholdNum = 10000L;

    //起始值
    private Long startNum;
    //截止值
    private Long endNum;

    public MyForkJoinTask(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    /**
     * 处理逻辑
     *
     * @return
     */
    @Override
    protected Long compute() {
        // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
        // 可以正式进行累加计算了
        if (endNum - startNum < thresholdNum) {
            System.out.println("开始计算的部分：startValue = " + startNum + ";endValue = " + endNum);
            Long totalValue = 0L;
            for (Long index = this.startNum; index <= this.endNum; index++) {
                totalValue += index;
            }
            return totalValue;
        } else {
            // 否则再进行任务拆分，拆分成两个任务 此处相当于递归 调用(核裂变 1 -> 2 -> 4 -> 8......)
            MyForkJoinTask subTask1 = new MyForkJoinTask(startNum, (startNum + endNum) / 2);
            subTask1.fork();
            MyForkJoinTask subTask2 = new MyForkJoinTask((startNum + endNum) / 2 + 1, endNum);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }
    }
}