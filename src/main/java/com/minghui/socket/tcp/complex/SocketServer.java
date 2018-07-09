package com.minghui.socket.tcp.complex;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 套接字服务端
 *
 * @author minghui.y
 * @create 2018-07-05 23:48
 **/
public class SocketServer {

    private static final int SERVER_PORT = 8001;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket client = null;
        ThreadPoolExecutor executor = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
//            executor = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

            //不断响应客户端
            while (true) {
                client = serverSocket.accept();
                System.out.println("服务端连接到客户端...");
                SocketRunnable task = new SocketRunnable(client);
//                executor.execute(task);
                new Thread(task).start();
            }

        } catch (Exception e) {
            System.out.println("服务端连接失败...");
            e.printStackTrace();
        }

    }
}
