package com.ceei.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer2 {
    public static void main(String[] args) {
        //实现每一次新建一个socket
        try{
            ServerSocket ss = new ServerSocket(8001);
            while(true)
            {
                Socket s = ss.accept();
                System.out.println("来了一个client");
                new Thread(new Worker(s)).start();
            }
            //ss.close
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
