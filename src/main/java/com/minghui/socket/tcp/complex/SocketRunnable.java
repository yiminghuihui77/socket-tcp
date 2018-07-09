package com.minghui.socket.tcp.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * 套接字任务
 * 服务端启动多线程执行任务
 * @author minghui.y
 * @create 2018-07-05 23:29
 **/
public class SocketRunnable implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketRunnable.class);

    private Socket socket;

    public SocketRunnable(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            this.socket.setSoTimeout(10000);
            boolean flag = true;
            //获取输入流，读取客户端数据
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取输出流，向客户端反馈数据
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String clientMsg = null;
            //执行循环体
            while (flag) {

                try {
                    //获取客户端数据
                    clientMsg = reader.readLine();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    writer.write("服务端读取超时...");
                    writer.flush();
                }

                if ("bye".equalsIgnoreCase(clientMsg)) {
                    flag = false;
                }
                //向客户端反馈数据
                if (clientMsg != null && !"".equals(clientMsg)) {
                    System.out.println("服务端收到：" + clientMsg);
                    writer.write("Server response : " + clientMsg);
                    writer.flush();
                }

            }
            //服务端确认关闭连接
            writer.write("Server confirm bye...");
            writer.flush();
            //关闭IO连接、套接字连接，释放资源
            reader.close();
            writer.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("服务端执行任务失败...");
            e.printStackTrace();
        }
    }
}
