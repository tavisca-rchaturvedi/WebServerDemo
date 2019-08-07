package com.tavisca.workshops;
import com.tavisca.workshops.responses.*;
import java.io.File;

public class RequestAndResponseHelper {

    public byte[] processAndCreateResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = new byte[0];
        System.out.println("isFile: " + new File(Responder.baseAddress + requestParser.getRequestURI().substring(1)).isFile());
        if(requestParser.getMethod().equals("GET")){
            if(requestParser.getRequestURI().trim().equals("/")){
                responseData = (new RootURIResponse()).getResponse(responseCreator, requestParser);
            }
            else if(new File(Responder.baseAddress + requestParser.getRequestURI().substring(1)).isFile()){
                if(requestParser.getRequestURI().substring(1).contains(".rishabh"))
                    responseData = (new CustomResourceFoundResponse()).getResponse(responseCreator, requestParser);
                else
                    responseData = (new ValidResourceFoundResponse()).getResponse(responseCreator, requestParser);
            }
            else{
                responseData = (new ErrorResponse()).getResponse(responseCreator, requestParser);
            }
        }
        return responseData;
    }
}
