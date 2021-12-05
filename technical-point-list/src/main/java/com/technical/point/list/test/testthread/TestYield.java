package com.technical.point.list.test.testthread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 14:48
 * @description: Yield：用来测试礼让线程
 *
 * <p>
 * 1、礼让线程,让当前正在执行的线程暂停,但不阻塞
 * 2、将线程状态由运行状态转化为就绪状态
 * 3、让CPU重新调度,礼让不一定成功，最终看CPU心情
 * </p>
 */
public class TestYield {
    public static void main(String[] args) throws Exception {
        //MyYield yield = new MyYield();
        //同时启动A和B线程
//        new Thread(yield, "A").start();
//        new Thread(yield, "B").start();

        //FileUtils.copyURLToFile(new URL("https://up.enterdesk.com/edpic_source/53/0a/da/530adad966630fce548cd408237ff200.jpg"), new File("1.jpg"));
        //FileUtils.toFile(new URL("https://up.enterdesk.com/edpic_source/53/0a/da/530adad966630fce548cd408237ff200.jpg"));
        //System.out.println(file.getName());
    }
}


class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行! hook");
        Thread.yield();//礼让线程
        System.out.println(Thread.currentThread().getName() + "结束执行! hook");
    }
}
