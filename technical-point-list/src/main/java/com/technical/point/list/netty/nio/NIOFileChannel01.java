package com.technical.point.list.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Mr.Gao
 * @date: 2022年04月27日 17:54
 * @description:将文本内容写入到一个文件中
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        //将文本内容写入到文件中
        String contentToWrite = "NIOFileChannel01,将内存写入文件中!";
        //创建一个写入流
        FileOutputStream outputStream = new FileOutputStream("D:\\workspace\\input01.txt");

        //获取一个FileChannel
        FileChannel channel = outputStream.getChannel();

        //创建一个Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 写入
        byteBuffer.put(contentToWrite.getBytes());

        //切换byteBuffer为读的状态
        byteBuffer.flip();

        //写入通道中
        channel.write(byteBuffer);

        //关闭流
        outputStream.close();
    }
}
