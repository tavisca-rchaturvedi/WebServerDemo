package com.tavisca.workshops;

import com.tavisca.workshops.responses.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class RequestAndResponseHelper {

    String baseAddress = "www/";
    public void processAndRespondToClient(ResponseCreator responseCreator, RequestParser requestParser, Socket clientSocket) throws IOException {
        byte[] responseData;
        if(requestParser.getMethod().equals("GET")){
            if(requestParser.getRequestURI().trim().equals("/")){
                responseData = (new RootURIResponse()).getResponse(responseCreator, requestParser);
            }
            else if(new File(this.baseAddress + requestParser.getRequestURI().substring(1)).isFile()){
                responseData = (new ValidResourceFoundResponse()).getResponse(responseCreator, requestParser);
            }
            else{
                responseData = (new ErrorResponse()).getResponse(responseCreator, requestParser);
            }
            clientSocket.getOutputStream().write(responseData);
        }
    }
}
