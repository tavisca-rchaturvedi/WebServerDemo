package com.tavisca.workshops;
import com.tavisca.workshops.logger.Log;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class Server implements Runnable {
    private final Socket clientSocket;

    private Server(Socket clientSocket){
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

    private void closeInputAndOutputStreams(BufferedReader requestReader, OutputStream outputStream) throws IOException {
        outputStream.close();
        requestReader.close();
        this.clientSocket.close();
    }

    public static void main(String[] args) throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(8000)) {
            Log.infoLog("Server waiting at port 8000");

            while(true){
                Socket socket = serverSocket.accept();
                Log.infoLog("Socket Accepted");
                Thread t = new Thread(new Server(socket));
                t.start();
            }
        }
        catch(Exception e){
            Log.warningLog(e.getMessage());
        }
    }
}
