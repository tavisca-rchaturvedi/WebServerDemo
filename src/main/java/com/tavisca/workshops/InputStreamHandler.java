package com.tavisca.workshops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputStreamHandler {

    public BufferedReader getInputStream(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void closeInputStream(BufferedReader bufferedReader) throws IOException {
        bufferedReader.close();
    }
}
