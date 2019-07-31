package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;

import java.io.File;
import java.io.IOException;

public class ValidResourceFoundResponse implements Responder {

    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) throws IOException {
        System.out.println("File exists with file name " + requestParser.getRequestURI().substring(1));
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + requestParser.getRequestURI().substring(1))).getBytes();
        System.out.println(responseData);
        return responseData;
    }
}
