package com.tavisca.workshops;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    Thread t;
    int port;

    public Server(int port){
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Server waiting at " + this.port);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
                String line = bufferedReader.readLine(), request = "";
                System.out.println(line);
                String[] query = line.split(" ");

                if(query[0].equals("GET")){
                    if(query[1].equals('/')){
                        printWriter.println("<html><body><h1>Hello</h1></body></html>");
                    }
                    else{
                        if(new File(query[1].substring(1)).isFile()){
//                            dataOutputStream.writeByte(new File);
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Thread t = new Thread(new Server(8080));
        t.start();
    }
}
