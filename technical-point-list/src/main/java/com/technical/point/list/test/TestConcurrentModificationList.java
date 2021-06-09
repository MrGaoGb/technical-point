package com.technical.point.list.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 面试难题：List 如何一边遍历，一边删除？
 * @Author gaogba
 * @Date 2020/8/24 14:08
 * @Version 1.0
 */
public class TestConcurrentModificationList {
    /**
     * 怎么将java文件生成class文件
     * 1.切换至java文件目录，执行javac TestConcurrentModificationList.java  -encoding utf-8 [包含中文可追加] 【编译程序】
     * 2.java TestConcurrentModificationList 【运行程序】
     * 3.javap TestConcurrentModificationList 【查看生成的class文件】
     */
    public static void main(String[] args) {
        List<String> platformList = new ArrayList<>();
        platformList.add("博客园");
        platformList.add("CSDN");
        platformList.add("掘金");
        /**
         * 下面的遍历其实是通过platformList.iterator()进行遍历的，其中next()方法中有一行代码是对并发变量进行校验的checkForComodification()方法
         * 1.在没有移除前modCount != expectedModCount,两个值均为3,
         * 2.此处表示调用的是重写Iterator迭代器java.util.ArrayList#remove(java.lang.Object)的方法，因此在执行platformList.remove()方法之后，即就是java.util.ArrayList#remove(java.lang.Object)，该方法会先调用java.util.ArrayList#fastRemove(int)，
         *   fastRemove()方法会先执行modCount++,此时modCount为4，而expectedModCount为3。
         * 3.此时java.util.ArrayList.Itr#checkForComodification()，会抛出java.util.ConcurrentModificationException(),并发修改异常。
         * 4.java.util.AbstractList#modCount，该变量modCount由ArrayList的父类定义protected transient int modCount = 0;
         * 5. 在每次调用java.util.ArrayList#add(java.lang.Object)时，会对父类modCount变量进行自增modCount++操作;
         */
        for (String platform : platformList) {
            if (platform.equals("博客园")) {
                platformList.remove(platform);
            }
        }

        /**
         * 既然不能使用foreach来实现，那么我们该如何实现呢？
         * 主要有以下3种方法：
         *
         *  1.使用Iterator的remove()方法
         *    a.java.util.ArrayList.Itr#remove()方法，第一步还是先进行checkForComodification()；
         *    b.关键代码如下:expectedModCount = modCount;//关键的赋值操作
         *  2.使用for循环正序遍历
         *  3.使用for循环倒序遍历
         */
        Iterator<String> iterator = platformList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (next.equals("博客园")) {
                iterator.remove();
            }
        }

    }
}
