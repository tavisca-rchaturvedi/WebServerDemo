package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;

import java.io.File;
import java.io.IOException;

public class RootURIResponse implements Responder {
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) throws IOException {
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + "OkResponse.html")).getBytes();
        System.out.println(responseData);
        System.out.println("This will send 200 response");
        return responseData;
    }
}
