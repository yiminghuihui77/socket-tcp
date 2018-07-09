package com.minghui.socket.udp.simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 发送者
 *
 * @author minghui.y
 * @create 2018-07-09 14:36
 **/
public class UDPSender {

    public static void main(String[] args) {

        DatagramSocket sender = null;
        DatagramPacket pack = null;
        BufferedReader reader = null;
        String message = "init finished.";
        try {
            sender = new DatagramSocket(3001);
            pack = new DatagramPacket(message.getBytes(), 0, message.length(), InetAddress.getLocalHost(), 3000);
            reader = new BufferedReader(new InputStreamReader(System.in));
            //发送初始化信息
            sender.send(pack);
            System.out.println("请输入信息：");
            while ((message = reader.readLine()) != null) {
                pack.setData(message.getBytes(), 0, message.length());
                sender.send(pack);
                if("exit".equalsIgnoreCase(message)) {
                    System.out.println("send no more message.");
                    break;
                }
                System.out.println("请输入信息：");
            }
            //释放连接
            reader.close();
            sender.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
