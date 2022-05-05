package com.technical.point.list.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 9:46
 * @description:先从一个文件A读取数据，再写入数据到另一个文件B中，在此操作中仅用一个Buffer进行操作
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        // 从文件A获取流
        File file = new File("D:\\workspace\\input02.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        // 获取输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\input03.txt");

        // 与管道建立联系
        FileChannel fileChannel02 = fileInputStream.getChannel();
        FileChannel fileChannel03 = fileOutputStream.getChannel();

        // 创建一个Buffer缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);

        // 不清楚文件内容大小 故采用循环读取
        while (true) {
            // 清除缓冲区 如果缺少此处 会导致处于循环之中
            byteBuffer.clear();
            // 将管道02的数据读取到缓冲区中
            int read = fileChannel02.read(byteBuffer);
            System.out.println("read=" + read);
            if (read == -1) break;
            // 由读转化为写(切换) 相当于读多少写多少
            byteBuffer.flip();
            // 将缓冲区的内容写入管道03
            fileChannel03.write(byteBuffer);
        }
        // 关闭流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
