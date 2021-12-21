package com.example.iot_serve;



//import com.example.iot_serve.config.ServerConfig;

import org.springframework.boot.CommandLineRunner;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketRunner{


    public static void main (String[] args) throws IOException {
        System.out.println("开启了------------------------------------------------------------------------");
        ServerSocket server =new ServerSocket(9999);
        while (true){
            Socket socket= server.accept();
            new hand(socket);

        }

    }
    public static class hand implements Runnable{
        private Socket socket;

        public hand(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            DataInputStream input = null;
            try {
                input = new DataInputStream(socket.getInputStream());
                String clientInputStr = input.readUTF();
                System.out.println("客户端发过来的内容:" + clientInputStr);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
