package com.technical.point.list.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Mr.Gao
 * @date: 2021/9/6 19:57
 * @description:
 */
public class TestOioServer {
    public static void main(String[] args) throws IOException {
        //创建Socket服务 监听10101
        ServerSocket server = new ServerSocket(10101);
        System.out.println("服务器启动!");
        while (true) {
            //获取一个套接字
            final Socket accept = server.accept();
            System.out.println("来一个客户端!");
            //执行业务处理
            handle(accept);
        }
    }

    private static void handle(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            final InputStream inputStream = socket.getInputStream();
            while (true) {
                //逻辑处理
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                }else {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("socket 关闭!");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
