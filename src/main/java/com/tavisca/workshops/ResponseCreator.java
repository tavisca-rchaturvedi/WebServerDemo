package com.tavisca.workshops;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class ResponseCreator {

    public String fileResponse(File fileToSend) throws IOException {

        String response = "HTTP/1.1 200 OK\r\n";
        response += getCommonResponse(fileToSend);
        return response;
    }

    public String method404(File fileToSend) throws IOException {
        String response = "HTTP/1.1 404\r\n";
        response += getCommonResponse(fileToSend);

        return response;
    }

    public String getCommonResponse(File fileToSend) throws IOException {
        String response = "Server: My Java HTTP Server: 1.0\r\n";
        response += "Date: " + (new Date()).toString() + "\r\n";
        response += "Content-Type: text/html\r\n";
        response += "Content-length: " + String.join("",Files.readAllLines(fileToSend.toPath())).length() +"\r\n";
        response += "\r\n";
        response += String.join("",Files.readAllLines(fileToSend.toPath()));

        return response;
    }

}
