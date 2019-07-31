package com.tavisca.workshops;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    Thread t;
    int port;
    Socket clientSocket;

    String baseAddress = "";

    public Server(int port, Socket clientSocket){
        this.port = port;
        this.clientSocket = clientSocket;
    }

    public void run(){

        RequestHandler requestHandler;
        ResponseHandler responseHandler;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            String line = bufferedReader.readLine();
            requestHandler = new RequestHandler(line);
            responseHandler = new ResponseHandler();
            System.out.println(Thread.currentThread().getId() + " is the thread running for " + this.clientSocket.getLocalPort() + " with request " + requestHandler.getRequestURI());

            if(requestHandler.getMethod().equals("GET")){

                if(requestHandler.getRequestURI().trim().equals("/")){
                    this.clientSocket.getOutputStream().write(responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")).getBytes());
                    System.out.println(responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")));
                    System.out.println("This will send 200 response");
                }
                else{
                    System.out.println("The uri for this request is " + requestHandler.getRequestURI());
                    if(new File(this.baseAddress + requestHandler.getRequestURI().substring(1)).isFile()){
                        System.out.println("File exists with file name " + requestHandler.getRequestURI().substring(1));
                        System.out.println(responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")));
                        this.clientSocket.getOutputStream().write(responseHandler.fileResponse(new File(this.baseAddress + requestHandler.getRequestURI().substring(1))).getBytes());

                    }
                    else{
                        System.out.println("File does not exist");
                        System.out.println(responseHandler.method404(new File(this.baseAddress + "404.html")));
                        this.clientSocket.getOutputStream().write(responseHandler.method404(new File(this.baseAddress + "404.html")).getBytes());
                    }
                }
            }
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
