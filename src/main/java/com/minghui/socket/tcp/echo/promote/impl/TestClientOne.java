package com.minghui.socket.tcp.echo.promote.impl;

/**
 * 客户端1
 * @author minghui.y
 * @create 2018-07-09 11:47
 **/
public class TestClientOne {

    public static void main(String[] args) {
        MultiClient client1 = new MultiClient("clientOne");
        client1.exchangeWithServer("localhost", 12990);

    }
}
