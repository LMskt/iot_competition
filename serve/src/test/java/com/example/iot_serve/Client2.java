package com.example.iot_serve;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {
    public static final String IP_ADDR = "localhost";//服务器地址
    public static final int PORT = 9999;//服务器端口号

//    public static void main(String[] args) {
//        try {
//            Socket s=new Socket(IP_ADDR,PORT);
//            //读取服务器端数据
//            DataInputStream input = new DataInputStream(s.getInputStream());
//            String ret="";
//            while (true){
//                byte giao=input.readByte();
//               char en= (char) giao;
//                ret+=en;
//               if(en=='}'){
//                   System.out.println(ret);
//
//                   ret="";
//               }
//            }
//        } catch (IOException  e) {
//            e.printStackTrace();
//        }
//
//    }


public static void main(String[] args) {
    try {
        Socket s=new Socket(IP_ADDR,PORT);
        //读取服务器端数据
        DataInputStream input=new DataInputStream(s.getInputStream());
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        Scanner in=new Scanner(System.in);
        while (true){
//           System.out.println("输入光照");
//           String a=in.nextLine();
//            System.out.println("输入温度");
//            String b=in.nextLine();
//            System.out.println("输入湿度");
//            String c=in.nextLine();
//            String en="{temperature:"+b+",illumination:"+a+",humidity:"+c+"}";
            String en="{temperature:12,illumination:13,humidity:14}";
            byte[] enn=en.getBytes();
            out.write(enn);
            String ret="";
            while (true){
                byte giao=input.readByte();
               char an= (char) giao;
                ret+=an;
               if(an=='}'){
                   System.out.println(ret);

                   ret="";
               }


        }
    }
} catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }}



}
