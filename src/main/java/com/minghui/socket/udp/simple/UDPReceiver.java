package com.minghui.socket.udp.simple;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP接收者
 *
 * @author minghui.y
 * @create 2018-07-09 14:31
 **/
public class UDPReceiver {

    public static void main(String[] args) {

        DatagramSocket socket = null;
        DatagramPacket pack = null;
        try {
            socket = new DatagramSocket(3000);

            while (true) {
                //这一步待优化，耗内存！
                pack = new DatagramPacket(new byte[1024], 1024);
                socket.receive(pack);
                String message = new String(pack.getData()).trim();
                System.out.println("接收到：" + message);
                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("receive no more info");
                    break;
                }

            }
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
