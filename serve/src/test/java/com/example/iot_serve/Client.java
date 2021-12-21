package com.example.iot_serve;

import org.springframework.boot.configurationprocessor.json.JSONObject;
//import net.sf.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 9998;//服务器端口号

    public static void main(String[] args) {
        System.out.println("客户端启动...");
        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");
        while (true) {
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);

                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                System.out.print("请输入: \t");
//                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                String js="{value:"+str+"}";
//                System.out.println("客户端："+js);
//                out.writeUTF(js);

                String ret = input.readUTF();
                System.out.println("服务器端返回过来的是: " + ret);
//                Thread.sleep(3000);
                // 如接收到 "OK" 则断开连接
                if ("OK".equals(ret)) {
                    System.out.println("客户端将关闭连接");
                    Thread.sleep(500);
                    break;
                }

                out.close();
                input.close();
            } catch (Exception e) {
                System.out.println("客户端异常:" + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
