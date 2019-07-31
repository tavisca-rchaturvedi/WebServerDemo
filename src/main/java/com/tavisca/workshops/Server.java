package com.tavisca.workshops;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    int port;
    Socket clientSocket;

    public Server(int port, Socket clientSocket){
        this.port = port;
        this.clientSocket = clientSocket;
    }

    public void run(){

        RequestAndResponseHelper requestAndResponseHelper = new RequestAndResponseHelper();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            RequestHandler requestHandler = new RequestHandler(bufferedReader.readLine());
            System.out.println(Thread.currentThread().getId() + " is the thread running for " + this.clientSocket.getLocalPort() + " with request " + requestHandler.getRequestURI());
            requestAndResponseHelper.processAndRespondToClient(new ResponseHandler(), requestHandler, this.clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server waiting at port 8080");

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Socket Accepted");
            Thread t = new Thread(new Server(8080, socket));
            t.start();
        }

    }
}
