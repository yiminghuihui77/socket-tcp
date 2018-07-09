package com.minghui.socket.tcp.echo.promote.impl;

/**
 * 客户端2
 * @author minghui.y
 * @create 2018-07-09 12:03
 **/
public class TestClientTwo {
    public static void main(String[] args) {

        MultiClient client2 = new MultiClient("clientTwo");
        client2.exchangeWithServer("localhost", 12990);
    }
}
