package com.tavisca.workshops;
import com.tavisca.workshops.logger.Log;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;


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
            StreamsHandler streamsHandler = new StreamsHandler();
            BufferedReader bufferedReader = streamsHandler.getInputStream(this.clientSocket);
            OutputStream outputStream = streamsHandler.getOutputStream(this.clientSocket);
            RequestParser requestParser = new RequestParser(bufferedReader.readLine());
            Log.getLogger().log(Level.INFO, Thread.currentThread().getId() + " is the thread running for " + this.clientSocket.getLocalPort() + " with request " + requestParser.getRequestURI());
            byte[] responseData = requestAndResponseHelper.processAndRespondToClient(new ResponseCreator(), requestParser);
            outputStream.write(responseData);

            this.clientSocket.close();
            streamsHandler.closeInputStream(bufferedReader);
            streamsHandler.closeOutputStream(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Log.getLogger().log(Level.INFO,"Server waiting at port 8080");

        while(true){
            Socket socket = serverSocket.accept();
            Log.getLogger().log(Level.INFO,"Socket Accepted");
            Thread t = new Thread(new Server(8080, socket));
            t.start();
        }

    }
}
