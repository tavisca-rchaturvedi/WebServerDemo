package com.tavisca.workshops;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseCreator {

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

    private String getCommonResponse(File fileToSend) throws IOException {
        Pattern pattern = Pattern.compile("(.*).jpeg|(.*).png|(.*).jpg|(.*).jfif", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileToSend.getName());
        String response = "Server: My Java HTTP Server: 1.0\r\n";
        response += "Date: " + (new Date()).toString() + "\r\n";

        if(matcher.find()){
            response += prepareAndReturnImageResponse(fileToSend);
        }
        else{
            response += "Content-Type: text/html\r\n";
            response += "Content-length: " + String.join("",Files.readAllLines(fileToSend.toPath())).length() +"\r\n";
            response += "\r\n";
            response += String.join("",Files.readAllLines(fileToSend.toPath()));
        }
        return response;
    }

    private String prepareAndReturnImageResponse(File fileToSend) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(fileToSend.getPath()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpeg", bos );
        byte [] data = bos.toByteArray();
        String encodedFile = new String(Base64.getEncoder().encode(data));

        String htmlResponse = "<html><head></head><body><img src=\"data:image/jpeg;base64," + encodedFile + "\" alt=\"" + fileToSend.getName()+"\"></body></html>";
        String response = "Content-Type: text/html\r\n";
        response += "Content-length: " +htmlResponse.length() +"\r\n";
        response += "\r\n";
        response += htmlResponse;
        return response;
    }


}
