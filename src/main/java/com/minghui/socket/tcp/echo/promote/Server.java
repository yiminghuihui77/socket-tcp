package com.minghui.socket.tcp.echo.promote;

import java.net.Socket;

/**
 * 服务端接口
 *
 * @author minghui.y
 * @create 2018-07-09 10:34
 **/
public interface Server {
    void exchangeWithClient(Socket client);
}
