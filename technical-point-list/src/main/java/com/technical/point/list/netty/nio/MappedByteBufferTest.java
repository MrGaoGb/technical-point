package com.technical.point.list.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Mr.Gao
 * @date: 2022年04月29日 14:37
 * @description: MappedByteBuffer可以让文件在内存(堆外内存)中直接修改，即操作系统不需要再拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\workspace\\input01.txt", "rw");
        //获取对应的通道
        final FileChannel fileChannel = randomAccessFile.getChannel();
        /**
         * 参数1：表示FileChannel.MapMode.READ_WRITE使用的是读写模式
         * 参数2：可以直接修改的起始位置
         * 参数3：映射到内存的大小(而不是索引位置)，即将input01.txt的多少个字节映射到内存，可以修改的范围是0-5
         */
        final MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 20);
        mappedByteBuffer.put(1, (byte) 'H');
        mappedByteBuffer.put(15, (byte) 8);

        randomAccessFile.close();
        System.out.println("修改成功!");
    }
}
