package com.technical.point.list.netty.nio;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: Mr.Gao
 * @date: 2022年04月29日 15:08
 * @description: Scattering:将数据写入Buffer时,可以采用buffer数组，依次写入 -> [分散]
 * Gathering:从buffer读取数据时, 可以采用buffer数组，依次读出 -> [聚集]
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws Exception {
        //使用ServerSocketChannel和SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress(8888);
        //绑定端口到Socket并启动
        serverSocketChannel.socket().bind(socketAddress);

        //创建buffer数组
        final ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        System.out.println("服务端已启动! 等待客户端连接!");
        //等待客户端链接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;//假定客户端信息长度为8
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                //将数据写入buffer中
                long read = socketChannel.read(byteBuffers);
                byteRead += read; //累计读到的字节数
                System.out.println("byteRead =" + byteRead + ", curRead=" + read);
                Arrays.asList(byteBuffers).stream()
                        .map(bf -> "position=" + bf.position() + ", limit=" + bf.limit())
                        .forEach(System.out::println);
            }

            //反转
            Arrays.asList(byteBuffers).forEach(bf -> bf.flip());


            // 将数据反显在客户端
            int byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
                System.out.println("byteWrite =" + byteWrite + ", curWrite=" + write);
                Arrays.asList(byteBuffers).stream()
                        .map(bf -> "position=" + bf.position() + ", limit=" + bf.limit())
                        .forEach(System.out::println);
            }

            // 将缓冲区重置
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());
            System.out.println("byteRead =" + byteRead + ", byteWrite=" + byteWrite + ", messageLength=" + messageLength);
        }
    }
}
