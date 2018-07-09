package com.minghui.socket.tcp.echo.promote.impl;

/**
 * @author minghui.y
 * @create 2018-07-09 11:41
 **/
public class TestServer {

    public static void main(String[] args) {

        //创建一个服务端
        MultiServer server = new MultiServer(12990);
        server.run();

    }

}
