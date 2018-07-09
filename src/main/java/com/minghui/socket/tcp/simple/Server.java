package com.minghui.socket.tcp.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 基于TCP/IP协议的socket编程
 * @author minghui.y
 * @create 2018-07-05 18:28
 **/
public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        //服务器端口
        final int SERVER_PORT = 8001;

        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            //与客户端建立连接前一直阻塞
            Socket socket = server.accept();
            Reader reader = new InputStreamReader(socket.getInputStream());
            char[] buffer = new char[1024];
            int hasRead = 0;
            StringBuffer msg = new StringBuffer("read from client：");
            while ((hasRead = reader.read(buffer)) != -1) {
                msg.append(new String(buffer, 0, hasRead));
            }
            LOGGER.info(msg.toString());
            reader.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }


    }
}
