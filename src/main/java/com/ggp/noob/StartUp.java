package com.ggp.noob;

import java.io.*;
import java.net.InetAddress;
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
                try {
                    OutputStream clientOutputStream = null;
                    InputStream  clientInputStream = null;
                    Socket proxySocket = null;
                    OutputStream proxyOutputStream = null;
                    InputStream proxyInputStream = null;
                    String iNetAddress = "";
                    StringBuilder request = new StringBuilder();
                    clientOutputStream = socket.getOutputStream();
                    clientInputStream = socket.getInputStream();
                    String line = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientInputStream));

                    while (null !=(line = reader.readLine())){
                        System.out.println(line);
                        request.append(line).append("\r\n");
                        if(line.length() == 0){
                            break;
                        }else if(line.contains("Host")){
                            iNetAddress = line;
                        }
                    }
                    String requestType = request.substring(0,request.indexOf(" "));
                    String[] temp = iNetAddress.split(":");
                    String host = temp[1];
                    int port = 80;
                    if(temp.length == 3){
                        port = Integer.valueOf(temp[2]);
                    }
                    System.out.println(host);
                    System.out.println(port);
                    System.out.println(requestType);
                    proxySocket = new Socket(host.trim(),port);
                    proxyInputStream = proxySocket.getInputStream();
                    proxyOutputStream = proxySocket.getOutputStream();
                    if(requestType.equalsIgnoreCase("CONNECT")){
                        clientOutputStream.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                        clientOutputStream.flush();
                    }else{
                        proxyOutputStream.write(request.toString().getBytes());
                    }
                    /**
                     * 已经建立过通道了，不需要在握手了
                     */
                    new ProxyHandler(clientInputStream,proxyOutputStream).start();
                    while (true){
                        clientOutputStream.write(proxyInputStream.read());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

    }
}
