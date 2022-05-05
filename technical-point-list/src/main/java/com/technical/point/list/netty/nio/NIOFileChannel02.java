package com.technical.point.list.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Mr.Gao
 * @date: 2022年04月27日 18:07
 * @description:从一个文件中读取内容
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        // 从文件中获取输入流数据
        File file = new File("D:\\workspace\\input01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取一个FileChannel通道
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建一个buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将通道的内容写入读到buffer
        fileChannel.read(byteBuffer);

        //输出结果
        System.out.println(new String(byteBuffer.array()));

        //关闭流
        fileInputStream.close();
    }
}
