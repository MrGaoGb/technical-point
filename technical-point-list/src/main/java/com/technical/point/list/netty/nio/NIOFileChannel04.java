package com.technical.point.list.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 11:08
 * @description:使用transferFrom进行文件拷贝
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        //读取图片数据
        FileInputStream fileInputStream = new FileInputStream("D:\\workspace\\a.png");
        // 创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\b.png");
        //获取对应的fileChannel
        FileChannel sourceFileChannel = fileInputStream.getChannel();
        FileChannel destFileChannel = fileOutputStream.getChannel();

        //进行copy
        destFileChannel.transferFrom(sourceFileChannel, 0, sourceFileChannel.size());

        // 关闭对应的流
        destFileChannel.close();
        fileOutputStream.close();
        sourceFileChannel.close();
        fileInputStream.close();
    }
}
