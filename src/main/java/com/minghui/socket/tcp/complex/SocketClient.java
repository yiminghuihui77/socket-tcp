package com.minghui.socket.tcp.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 * 客户端不断向服务端发送信息，信息来自键盘输入，同时接收服务端发来的数据
 * 客户端发送"bye"触发连接断开
 * @author minghui.y
 * @create 2018-07-05 23:01
 **/
public class SocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketClient.class);

    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 8001;

    public static void main(String[] args) {
        boolean flag = true;
        Socket client = null;
        try {
            client = new Socket(SERVER_IP, SERVER_PORT);
            client.setSoTimeout(10000);
            //获取键盘输入
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            //获取输入流，用于读取服务端信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //获取输出流，用于向服务端发送数据
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            //执行循环体
            while (flag) {
                System.out.println("请输入一行信息：");
                //获取键盘输入，并判断
                String inputMsg = input.readLine();
                if ("bye".equalsIgnoreCase(inputMsg)) {
                    flag = false;
                }
                //将数据发送给服务端
                if (inputMsg != null && !"".equals(inputMsg)) {
                    writer.write(inputMsg);
                    writer.flush();
                }
                //获取服务端发来的数据
                try {
                    String serverMsg = reader.readLine();
                    if (serverMsg != null) {
                        System.out.println("接收到服务端信息：" + serverMsg);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            //关闭IO流、套接字连接，释放资源
            input.close();
            reader.close();
            writer.close();
            client.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
