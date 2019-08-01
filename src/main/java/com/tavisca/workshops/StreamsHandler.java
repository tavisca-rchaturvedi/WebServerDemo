package com.tavisca.workshops;

import java.io.*;
import java.net.Socket;

public class StreamsHandler {

    public BufferedReader getInputStream(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void closeInputStream(BufferedReader bufferedReader) throws IOException {
        bufferedReader.close();
    }

    public OutputStream getOutputStream(Socket clientSocket) throws IOException {
        return clientSocket.getOutputStream();
    }

    public void closeOutputStream(OutputStream outputStream) throws IOException {
        outputStream.close();
    }
}
