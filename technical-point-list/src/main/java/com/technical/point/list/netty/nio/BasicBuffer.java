package com.technical.point.list.netty.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;

/**
 * @author: Mr.Gao
 * @date: 2022年04月26日 11:32
 * @description:
 */
public class BasicBuffer {
    public static void main(String[] args) {

        // 创建一个buffer 大小为10 可以存放10个int
        IntBuffer buffer = IntBuffer.allocate(5);
        //写入
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 5);
        }

        //切换读取
        buffer.flip();

        Arrays.stream(buffer.array()).forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~NEW!");
        buffer.position(1);//指定新的position开始位置
        buffer.limit(3);//指定新的limit截止位置
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        //Arrays.stream(buffer.array()).forEach(System.out::println);
    }
}
