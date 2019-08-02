package com.tavisca.workshops;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class ResponseCreator {

    public String customFileResponse(String fileContent){
        String response = "HTTP/1.1 200 OK\r\n";
        response += "Server: My Java HTTP Server: 1.0\r\n";
        response += "Date: " + (new Date()).toString() + "\r\n";
        response += "Content-Type: text/html\r\n";
        response += "Content-length: " + fileContent.length() + "\r\n";
        response += "\r\n";
        response += fileContent;

        return response;
    }

    public String fileResponse(File fileToSend)  {
        String response = "HTTP/1.1 200 OK\r\n";
        try {
            response += getCommonResponse(fileToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String method404(File fileToSend) {
        String response = "HTTP/1.1 404\r\n";
        try {
            response += getCommonResponse(fileToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
