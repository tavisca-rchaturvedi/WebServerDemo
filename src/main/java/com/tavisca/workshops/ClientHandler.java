package com.tavisca.workshops;

import com.tavisca.workshops.logger.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandler {

    public void respondToClient(byte[] responseData, OutputStream outputStream) throws IOException {
        outputStream.write(responseData);
    }

    public byte[] prepareResponseFromRequest(BufferedReader requestReader, Socket clientSocket) throws IOException {
        RequestParser requestParser = new RequestParser(requestReader.readLine());
        Log.infoLog(Thread.currentThread().getId() + " is the thread running for " + clientSocket.getLocalPort() + " with request " + requestParser.getRequestURI());
        return (new RequestAndResponseHelper()).processAndCreateResponse(new ResponseCreator(), requestParser);
    }


}
