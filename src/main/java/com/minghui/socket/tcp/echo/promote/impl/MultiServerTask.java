package com.minghui.socket.tcp.echo.promote.impl;

import com.minghui.socket.tcp.echo.promote.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 多线程版服务端
 *
 * @author minghui.y
 * @create 2018-07-09 11:04
 **/
public class MultiServerTask implements Runnable, Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiServerTask.class);

    private Socket client;

    private String clientInfo;

    public MultiServerTask(Socket client) {
        this.client = client;
    }


    @Override
    public void exchangeWithClient(Socket client) {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);

            //接收客户端自身信息
            this.clientInfo = reader.readLine();
            LOGGER.info("The server connect to the client:" + this.clientInfo);
            //向客户端发送提示语
            writer.println("welcome, enter bye to exit.");
        } catch (Exception e) {
            LOGGER.error("The server can not connect to the client!");
            System.exit(1);
        }

        String userMsg = null;
        try {
            //保持与一个客户端交互
            while (true) {
                //读取客户端数据
                userMsg = reader.readLine();
                if (userMsg == null) {
                    //读取值为null，说明通信中断
                    break;
                }
                System.out.println("The Server receive from ( " + this.clientInfo + "): " + userMsg);
                //返回前缀echo
                writer.println("echo:" + userMsg);
                //响应客户端的断开请求
                if ("bye".equalsIgnoreCase(userMsg)) {
                    writer.println("The server confirm to bye.");
                    LOGGER.info("The server disconnect with the client: " + this.clientInfo);
                    break;
                }
            }
            //断开连接，释放资源
            reader.close();
            writer.close();
            client.close();
        } catch (Exception e) {
            LOGGER.error("The Server run with wrong!");
        }

    }

    /**
     * 执行任务
     */
    @Override
    public void run() {
        exchangeWithClient(this.client);
    }
}
