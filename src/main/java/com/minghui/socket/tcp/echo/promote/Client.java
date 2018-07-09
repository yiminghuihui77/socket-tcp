package com.minghui.socket.tcp.echo.promote;

/**
 * 客户端接口
 *
 * @author minghui.y
 * @create 2018-07-08 10:53
 **/
public interface Client {
    void exchangeWithServer(String serverIp, int serverPort);
}
