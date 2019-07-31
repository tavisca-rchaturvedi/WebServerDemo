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

        try {
            RequestAndResponseHelper requestAndResponseHelper = new RequestAndResponseHelper();
            InputStreamHandler inputStreamHandler = new InputStreamHandler();
            BufferedReader bufferedReader = inputStreamHandler.getInputStream(this.clientSocket);
            RequestParser requestParser = new RequestParser(bufferedReader.readLine());
            System.out.println(Thread.currentThread().getId() + " is the thread running for " + this.clientSocket.getLocalPort() + " with request " + requestParser.getRequestURI());
            requestAndResponseHelper.processAndRespondToClient(new ResponseCreator(), requestParser, this.clientSocket);

            this.clientSocket.close();
            bufferedReader.close();

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
