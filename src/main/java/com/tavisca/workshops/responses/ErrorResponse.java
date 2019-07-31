package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;


import java.io.File;
import java.io.IOException;

public class ErrorResponse implements Responder {
    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) throws IOException {
        System.out.println("File does not exist");
        byte[] responseData = responseCreator.method404(new File(this.baseAddress + "404.html")).getBytes();
        System.out.println(responseData);
        return responseData;
    }
}
