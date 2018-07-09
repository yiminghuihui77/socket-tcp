package com.minghui.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 *
 * @author minghui.y
 * @create 2018-07-08 9:51
 **/
public class EchoServer {

    //监听端口
    private static final int LISTEN_PORT = 12990;

    public static void main(String[] args) {

        //创建服务端
        ServerSocket socketServer = null;
        try {
            socketServer = new ServerSocket(LISTEN_PORT);
        } catch (Exception e) {
            System.out.println("Can not listen the 12990 port!");
            System.exit(1);
        }

        //准备操作
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client = null;
        while (true) {
            //开始连接客户端
            try {
                client = socketServer.accept();
                //创建IO流对象
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
                //服务端提示欢迎语
                writer.println("welcome, I am the Server. Enter bye to exit.");
                //保持与一个客户端持续交互，直到客户端键入bye
                while (true) {
                    //读取客户端数据
                    String clientMsg = reader.readLine();
                    //根据读取的数据分情况
                    if (clientMsg == null) {
                        //读取为null,说明连接已断开
                        break;
                    } else {
                        //将客户端数据添加"Echo:"前缀后返回
                        writer.println("Echo：" + clientMsg);
                        System.out.println("服务端接收：" + clientMsg);
                        //判断客户端数据是否为bye
                        if("bye".equalsIgnoreCase(clientMsg)) {
                            writer.println("The server confirm to bye.");
                            break;
                        }
                    }
                }
                //关闭连接，释放资源
                reader.close();
                writer.close();
                client.close();
                socketServer.close();

            } catch (Exception e) {
                System.out.println("Can not connect to a client!");
                System.exit(1);
            }

        }

    }
}
