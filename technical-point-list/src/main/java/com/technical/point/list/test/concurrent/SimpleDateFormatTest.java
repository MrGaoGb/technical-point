package com.technical.point.list.test.concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2022年05月13日 15:03
 * @description:测试SimpleDateFormat线程不安全问题
 */
public class SimpleDateFormatTest {

    //执行次数
    private static final Integer EXEC_COUNT = 1000;
    //同时运行线程数量
    private static final Integer THREAD_COUNT = 20;
    //SimpleDateFormat对象
    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //TODO ThreadLocal
    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static String parseStr = "2022-05-13";

    public static void main(String[] args) throws Exception {
        // --控制最大的线程数
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        // --类似于计数器
        final CountDownLatch countDownLatch = new CountDownLatch(EXEC_COUNT);

        // --创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // --循环
        for (int i = 0; i < EXEC_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        //TODO 1、采用局部变量法 避免并发异常 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        //TODO 2、采用同步锁，synchronized(simpleDateFormat) 损耗性能比较大(同一时刻仅能一个线程进行操作!)
                        //TODO 3、lock锁，Lock lock = new ReentrantLock(); 与synchronized原理一致
                        //TODO 4、使用ThreadLocal,存储每个线程拥有simpleDateFormat副本
                        //TODO 5、 通过DateTimeFormatter类解决线程安全问题 利用java8
                        //TODO 6、joda-time --第三方形式
                        /**综上所示：
                        -- 在解决SimpleDateFormat类的线程安全问题的几种方案中，局部变量法由于线程每次执行格式化时间时，都会创建
                        SimpleDateFormat类的对象，这会导致创建大量的SimpleDateFormat对象，浪费运行空间和消耗服务器的性能，因为JVM创建和
                        销毁对象是要耗费性能的。所以，不推荐在高并发要求的生产环境使用。
                        -- synchronized锁方式和Lock锁方式在处理问题的本质上是一致的，通过加锁的方式，使同一时刻只能有一个线程执行格式化日期和
                        时间的操作。这种方式虽然减少了SimpleDateFormat对象的创建，但是由于同步锁的存在，导致性能下降，所以，不推荐在高并发
                        要求的生产环境使用。
                        -- ThreadLocal通过保存各个线程的SimpleDateFormat类对象的副本，使每个线程在运行时，各自使用自身绑定的SimpleDateFormat对象，互不干扰，执行性能比较高，推荐在高并发的生产环境使用。
                        -- DateTimeFormatter是Java 8中提供的处理日期和时间的类，DateTimeFormatter类本身就是线程安全的，经压测，DateTimeFormatter类处理日期和时间的性能效果还不错（后文单独写一篇关于高并发下性能压测的文章）。所以，推荐在高并发
                        场景下的生产环境使用。
                        -- joda-time是第三方处理日期和时间的类库，线程安全，性能经过高并发的考验，推荐在高并发场景下的生产环境使用*/
                        threadLocal.get().parse("2022-05-14");
                        //simpleDateFormat.parse(parseStr);
                    } catch (ParseException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + "ParseException 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    } catch (NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + "NumberFormatException 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    //表示执行完毕，释放当前线程
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("信号量发生错误!");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期完成!");
    }
}
