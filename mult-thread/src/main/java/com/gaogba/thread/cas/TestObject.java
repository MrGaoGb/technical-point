package com.gaogba.thread.cas;

import com.gaogba.thread.entity.User;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Mr.Gao
 * @date: 2021/6/26 17:05
 * @description:
 */
public class TestObject {
    public static void main(String[] args) {
        /**
         * 问题:Object对象在内存中占用多少字节；
         * 答案：16个字节 = 8个字节(markword) + 开启字节压缩4个字节(class point) + 4个字节填充(padding)
         */
//        Object o = new Object();
        /**
         * User对象在内存中占用多少字节
         * 答案: 24字节 = 8个字节(markword) + 默认开启字节压缩4个字节(class point) + 4个字节(Integer::实例数据) + 4个字节(String::实例数据) +  4个字节(Long::实例数据)
         */
        User o = new User();
        /**
         * 打印Java对象在内存中的存储布局
         *参考CAS原理学习.md ->>>> 2、Java 对象在内存中的存储布局
         */
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /**
         * synchronized对象信息存储在markword(对象头)里面
         */
//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }
    }
}
