package com.minghui.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 *
 * @author minghui.y
 * @create 2018-07-08 10:15
 **/
public class EchoClient {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12990;

    public static void main(String[] args) {
        Socket client = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        BufferedReader input = null;
        //连接服务器
        try {
            client = new Socket(SERVER_IP, SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            //读取服务端提示语
            System.out.println(reader.readLine());
        } catch (Exception e) {
            System.out.println("Can not connect the server!");
            System.exit(1);
        }
        //不断读取键盘输入、发送到服务端、读取服务端数据
        String userInput = null;
        input = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("请输入信息：");
            while ((userInput = input.readLine()) != null) {
                writer.println(userInput);
                System.out.println(reader.readLine());
                if ("bye".equalsIgnoreCase(userInput)) {
                    //读取服务端关闭提示
                    System.out.println(reader.readLine());
                    break;
                }
                System.out.println("请输入信息：");
            }
            //关闭连接，释放资源
            reader.close();
            writer.close();
            input.close();
            client.close();
        } catch (Exception e) {
            System.out.println("the client execute the task fail!");
        }

    }
}
