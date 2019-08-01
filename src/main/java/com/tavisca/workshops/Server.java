package com.tavisca.workshops;
import com.tavisca.workshops.logger.Log;
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
            ClientHandler clientHandler = new ClientHandler();
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = this.clientSocket.getOutputStream();
            byte[] responseData = clientHandler.prepareResponseFromRequest(requestReader, this.clientSocket);
            clientHandler.respondToClient(responseData, outputStream);
            this.closeInputAndOutputStreams(requestReader, outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeInputAndOutputStreams(BufferedReader requestReader, OutputStream outputStream) throws IOException {
        outputStream.close();
        requestReader.close();
        this.clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Log.infoLog("Server waiting at port 8080");

        while(true){
            Socket socket = serverSocket.accept();
            Log.infoLog("Socket Accepted");
            Thread t = new Thread(new Server(8080, socket));
            t.start();
        }

    }
}
