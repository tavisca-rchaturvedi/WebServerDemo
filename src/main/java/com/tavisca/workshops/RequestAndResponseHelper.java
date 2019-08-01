package com.tavisca.workshops;
import com.tavisca.workshops.responses.*;
import java.io.File;

public class RequestAndResponseHelper {

    String baseAddress = "www/";
    public byte[] processAndCreateResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = new byte[0];
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
        }
        return responseData;
    }
}
