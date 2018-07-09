package com.minghui.socket.tcp.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * 客户端
 * 基于TCP/IP协议的socket编程
 * @author minghui.y
 * @create 2018-07-05 18:28
 **/
public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {

        //服务端端IP、端口号
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 8001;
        //创建客户端
        Socket client = null;
        try {
            client = new Socket(SERVER_IP, SERVER_PORT);
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write("say hello from the client.");
            writer.flush();
            writer.close();
            client.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }
}
