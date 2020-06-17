package com.ggp.noob;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:GGP
 * @Date:2020/6/17 20:51
 * @Description:
 */
public class StartUp {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(10000);
        while (true){
            new Handler(server.accept()).start();
        }
    }
    public static class Handler extends Thread{
        public Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            OutputStream clientOutputStream = null;
            InputStream  clientInputStream = null;
            Socket proxySocket = null;
            OutputStream proxyOutputStream = null;
            InputStream inputStream = null;

            try {
                clientOutputStream = socket.getOutputStream();
                clientInputStream = socket.getInputStream();
                String line = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientInputStream));
                while (null !=(line = reader.readLine())){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
