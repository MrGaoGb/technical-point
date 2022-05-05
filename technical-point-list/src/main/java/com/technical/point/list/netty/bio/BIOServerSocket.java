package com.technical.point.list.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Mr.Gao
 * @date: 2022年04月21日 18:36
 * @description: <p>
 * 采用: telnet ip 端口 ，模拟客户端连接(CMD DOS窗口)
 * 命令: ctrl + ] 键，切换客户端输入框
 * send + msg(发送信息)
 * </p>
 */
public class BIOServerSocket {
    public static void main(String[] args) throws Exception {
        //采用线程池的方式
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //创建ServerSocket 监听8888端口
        final ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("服务端启动,等待客户端连接......");

        while (true) {
            //TODO 在等待客户端连接的时候会阻塞在此处
            System.out.println(Thread.currentThread().getName() + "等待连接......");
            //获取Socket
            final Socket socket = serverSocket.accept();
            cachedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "客户端建立连接!");
                handler(socket);
            });
        }
    }

    /**
     * 处理客户端发送的消息
     *
     * @param socket
     */
    public static void handler(Socket socket) {
        InputStream inputStream = null;
        try {
            byte[] bytes = new byte[1024];
            inputStream = socket.getInputStream();
            while (true) {
                // TODO 表示有客户端连接之后，会阻塞在此处读取数据
                System.out.println(Thread.currentThread().getName() + ":建立成功之后，等待接收......");
                if (inputStream.read() == -1) break;
                int len = inputStream.read(bytes);
                System.out.println(Thread.currentThread().getName() + ":" + new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":关闭连接......");
        }
    }
}
