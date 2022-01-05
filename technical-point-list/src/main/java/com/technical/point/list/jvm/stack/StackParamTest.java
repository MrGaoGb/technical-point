package com.technical.point.list.jvm.stack;

/**
 * @author: Mr.Gao
 * @date: 2022年01月05日 20:52
 * @description:
 */
public class StackParamTest {
    public static void main(String[] args) {
        //返回虚拟机试图试用最大内存 以字节为单位
        final long max = Runtime.getRuntime().maxMemory();
        //返回JVM初始化总内存量
        final long total = Runtime.getRuntime().totalMemory();

        //max=3621MB ~= 4G
        System.out.println("max=" + max / (double) 1024 / 1024 + "MB");
        //total=245MB
        System.out.println("total=" + total / (double) 1024 / 1024 + "MB");

        //默认情况下：分配的最大内存是电脑的 1/4
    }
}
