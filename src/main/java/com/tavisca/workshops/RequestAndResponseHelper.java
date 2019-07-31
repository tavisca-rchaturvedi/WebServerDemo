package com.tavisca.workshops;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class RequestAndResponseHelper {

    String baseAddress = "www/";
    public void processAndRespondToClient(ResponseHandler responseHandler, RequestHandler requestHandler, Socket clientSocket) throws IOException {
        byte[] responseData;
        if(requestHandler.getMethod().equals("GET")){

            if(requestHandler.getRequestURI().trim().equals("/")){
                responseData = responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")).getBytes();
                System.out.println(responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")));
                System.out.println("This will send 200 response");
            }
            else{
                System.out.println("The uri for this request is " + requestHandler.getRequestURI());
                if(new File(this.baseAddress + requestHandler.getRequestURI().substring(1)).isFile()){
                    System.out.println("File exists with file name " + requestHandler.getRequestURI().substring(1));
                    System.out.println(responseHandler.fileResponse(new File(this.baseAddress + "OkResponse.html")));
                    responseData = responseHandler.fileResponse(new File(this.baseAddress + requestHandler.getRequestURI().substring(1))).getBytes();

                }
                else{
                    System.out.println("File does not exist");
                    System.out.println(responseHandler.method404(new File(this.baseAddress + "404.html")));
                    responseData = responseHandler.method404(new File(this.baseAddress + "404.html")).getBytes();
                }
            }
            clientSocket.getOutputStream().write(responseData);
        }
    }
}
