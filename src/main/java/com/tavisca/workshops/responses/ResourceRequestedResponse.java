package com.tavisca.workshops.responses;

import com.tavisca.workshops.RequestParser;
import com.tavisca.workshops.Responder;
import com.tavisca.workshops.ResponseCreator;

import java.io.File;
import java.io.IOException;

public class ResourceRequestedResponse implements Responder {



    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData;
        if(new File(Responder.baseAddress + requestParser.getRequestURI().substring(1)).isFile()){
            responseData = (new ValidResourceFoundResponse()).getResponse(responseCreator, requestParser);
        }
        else{
            responseData = (new ErrorResponse()).getResponse(responseCreator, requestParser);
        }
        return responseData;
    }
}
