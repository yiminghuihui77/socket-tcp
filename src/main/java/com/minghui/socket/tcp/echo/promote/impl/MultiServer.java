package com.minghui.socket.tcp.echo.promote.impl;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程版服务端
 *
 * @author minghui.y
 * @create 2018-07-09 11:35
 **/
public class MultiServer {

    private int serverPort;

    public MultiServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(serverPort);
            Socket client = null;
            while (true) {
                //获取一个客户端
                client = server.accept();
                new Thread(new MultiServerTask(client)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
