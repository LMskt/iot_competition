package com.example.iot_serve.util;

import com.example.iot_serve.entity.*;
import com.example.iot_serve.mapper.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketMain implements CommandLineRunner {
    @Resource
    private rfcard_one_m rfcardone;
    @Resource
    private rfcard_two_m rfcardtwo;
    @Resource
    private rfcard_history_m rfcardhistory;

    @Resource
    private float_one_m one;
    @Resource
    private float_two_m two;
    @Resource
    private float_three_m three;
    @Resource
    private actuator_one_m onem;
    @Resource
    private actuator_two_m twom;
    @Resource
    private actuator_three_m threem;
    @Resource
    private actuator_four_m fourm;

    @Resource
    private flag_one_m f_one;
    @Resource
    private flag_two_m f_two;
    @Resource
    private flag_three_m f_three;
    @Resource
    private flag_four_m f_four;



    private  boolean flag;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("开启了------9999-----服务器");
        ServerSocket server =new ServerSocket(9999);
        new Thread(()->{
            while (true){
                try {
                    Socket socket=server.accept();
                    DataOutputStream outt=new DataOutputStream(socket.getOutputStream());
                    System.out.println("9999有连接");
                    flag=true;
                    new runnable_socket(socket);
                    new runnable_socket_send(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public class runnable_socket implements Runnable{
        String getvalue="";

        private Socket socket;

        public runnable_socket(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataInputStream input=new DataInputStream(socket.getInputStream());
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                while (flag){
                    receive(input,out);
                }
            } catch (IOException  e) {
                e.printStackTrace();

            }

        }

        public void receive(DataInputStream input,DataOutputStream out){
            try {
                byte giao=input.readByte();
                char en=(char)giao;
                getvalue+=en;
                if (en=='}'){
                    String flag=getvalue.substring(1,4);
                    System.out.println(flag);
                    sensor_normal(getvalue);
                    System.out.println(getvalue);
                    getvalue="";

                }
            } catch (IOException e) {
                flag=false;
                System.out.println("9999连接断开");
                e.printStackTrace();
            }
        }
    }

    //普通传感器的判断
    public void sensor_normal(String getvalue)  {
        String temperature = null;
        String illumination = null;
        String humidity = null;
        String PID=null;
        String bad=null;
        try {
            JSONObject jsonObject = new JSONObject(getvalue);
            temperature = jsonObject.getString("Tin");
            illumination = jsonObject.getString("Tout");
            humidity = jsonObject.getString("LXin");
            PID=jsonObject.getString("PID");
            bad=jsonObject.getString("bad");
            System.out.println(temperature + 'd' + illumination + 'c' + humidity+'d'+PID);

            //直接在这里操作执行器的值
            Float diyi=Float.valueOf(temperature);
            Float dier=Float.valueOf(illumination);
            Float disan=Float.valueOf(humidity);

//            //阈值
//            Float yi=f_one.flag1get_last().getValue();
//            Float two=f_two.flag2get_last().getValue();
//            Float three=f_three.flag3get_last().getValue();
//
//            int enn=0;
//            int enn2=0;
//            int enn3=0;
//            if (diyi>yi){
//                //大于温度上限制冷
//                enn= (int) ((diyi-yi));
//            }else if(diyi<two){
//                //低于温度下限制冷
//                enn2= (int) (yi-diyi);
//            }
//
//            if (disan<three){
//                //光照低于阈值
//                 enn3= (int) (disan-three);
//            }
//
//            onem.insert(new actuator_one(enn));
//            twom.insert(new actuator_two(enn2));
//            threem.insert(new actuator_three(enn3));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        float_one f1 = new float_one(Float.parseFloat(temperature));
        float_two f2 = new float_two(Float.parseFloat(illumination));
        float_three f3 = new float_three(Float.parseFloat(humidity));
        flag_four ff=new flag_four(Float.parseFloat(bad));
        if(!PID.equals("00000000")){
            rfcard_one en=new rfcard_one(PID);
            rfcardone.insert(en);
        }
        f_four.insert(ff);
        one.insert(f1);
        two.insert(f2);
        three.insert(f3);

        System.out.println(temperature + 'd' + illumination + 'c' + humidity+'d'+PID);

    }
    //双rf传感器
    public void senser_rf2(String getvalue){
        String rfcard1= null;
        String rfcard2= null;
        try {
            JSONObject jsonObject = new JSONObject(getvalue);
            rfcard1=jsonObject.getString("rfcard1");
            rfcard2=jsonObject.getString("rfcard2");
            System.out.println(rfcard1);
            System.out.println(rfcard2);
            if (rfcardone.rfcard_one_getgeni1(rfcard1)==0){
                rfcardone.rfcard_one_update(1,rfcard1);
                rfcard_history history=new rfcard_history(rfcard1,rfcard2,0);
                rfcardhistory.insert(history);
                System.out.println("done");
            }else
            {
                rfcardone.rfcard_one_update(0,rfcard1);
                rfcard_history history=new rfcard_history(rfcard1,rfcard2,1);
                rfcardhistory.insert(history);
                System.out.println("done");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //单rfcard传感器
    public void senser_rf1(String getvalue){
        String rfcard=null;
        try {
            JSONObject jsonObject = new JSONObject(getvalue);
            rfcard=jsonObject.getString("PID");
            System.out.println(rfcard);
//            rfcardone.insert(new rfcard_one(rfcard));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public class runnable_socket_send implements Runnable{
        private Socket socket;

        public runnable_socket_send(Socket socket) {
            this.socket = socket;
            //发送数据线程是否开始
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataOutputStream out =new DataOutputStream(socket.getOutputStream());
                while (flag){
                    send(out);
                    Thread.sleep(5000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        public String sti(int a){
            if (a==1)
                return "1";
            else if(a==0)
                return "0";
            else if (a==2)
                return "2";
            else
                return "error";
        }
        public void send(DataOutputStream out){
            try {
                actuator_one ac1=onem.a1get_last();
                actuator_two ac2=twom.a2get_last();
                actuator_three ac3=threem.a3get_last();
                actuator_four ac4=fourm.a4get_last();
//
                String value11=String.valueOf(ac1.getValue());
                String value22=String.valueOf(ac2.getValue());
                String value33=String.valueOf(ac3.getValue());
                String value44=String.valueOf(ac4.getValue());

                flag_one one=f_one.flag1get_last();
                flag_two two=f_two.flag2get_last();
                flag_three three=f_three.flag3get_last();

                String value1=String.valueOf(one.getValue());
                String value2=String.valueOf(two.getValue());
                String value3=String.valueOf(three.getValue());
                System.out.println("asddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+value44+value11);

                Map<String,String> map=new HashMap<>();
                if(value11.equals("0")){
                    map.put("fan2",value22);
//                        map.put("fan3","40");
                    map.put("light",value33);
                }else {
                    map.put("fan1",value11);
//                        map.put("fan3","40");
                    map.put("light",value33);
                }

//                if (value44.equals("0")){
//                    if(value11.equals("0")){
//                        map.put("fan2",value22);
////                        map.put("fan3","40");
//                        map.put("light",value33);
//                    }else {
//                        map.put("fan1",value11);
////                        map.put("fan3","40");
//                        map.put("light",value33);
//                    }
//                }
                map.put("TinDH",value1);
                map.put("TinDL",value2);
                map.put("LXD",value3);



                JSONObject jsonObject=new JSONObject(map);
                byte[] ess=jsonObject.toString().getBytes();
                out.writeBytes(jsonObject.toString());
                System.out.println(jsonObject.toString());
            } catch (IOException e) {
                flag=false;
                e.printStackTrace();
            }
        }
    }

}
