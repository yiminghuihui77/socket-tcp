package com.minghui.socket.tcp.echo.promote.impl;

import com.minghui.socket.tcp.echo.promote.Client;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

/**
 * 多线程版客户端
 *
 * @author minghui.y
 * @create 2018-07-09 10:38
 **/
public class MultiClient implements Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiClient.class);

    /**
     * 每个客户端都有一个名称
     */
    private String name;

    /**
     * 每个客户端的唯一标识
     */
    private String uuid;

    public MultiClient(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public void exchangeWithServer(String serverIp, int serverPort) {

        Socket client = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //连接服务器
        try {
            client = new Socket(serverIp, serverPort);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            //向服务端发送客户端自身信息
            writer.println(this.name + ">>>" + this.uuid);
            //读取服务端的初始化提示语
            LOGGER.info(reader.readLine());

        } catch (Exception e) {
            LOGGER.error("Can not connect to the server!");
            System.exit(1);
        }

        //不断读取键盘输入，发送给服务端
        String inputMsg = null;
        String serverMsg = null;
        try {
            System.out.println("请输入信息：");
            while ((inputMsg = input.readLine()) != null) {
                //发送给服务端
                writer.println(inputMsg);
                //读取服务端信息
                serverMsg = reader.readLine();
                if (serverMsg == null) {
                    //服务端返回null，代表连接中断
                    break;
                }
                LOGGER.info("The client (" + this.name + ":" + this.uuid + ") receive : " + serverMsg);
                //用户输入bye，表示中断通信
                if ("bye".equalsIgnoreCase(inputMsg)) {
                    LOGGER.info("The client (" + this.name + ":" + this.uuid + " is going to exit.");
                    LOGGER.info(reader.readLine());
                    break;
                }
                System.out.println("请输入信息：");
            }
            //断开连接，释放资源
            reader.close();
            writer.close();
            input.close();
            client.close();
        } catch (Exception e) {
            LOGGER .error("The client (" + this.name + ":" + this.uuid + ") is run with wrong!");
            System.exit(1);
        }

    }
}
