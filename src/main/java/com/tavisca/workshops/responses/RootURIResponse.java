package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;

public class RootURIResponse implements Responder {
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + "OkResponse.html")).getBytes();
        Log.infoLog(responseCreator.fileResponse(new File(this.baseAddress + "OkResponse.html")));
        Log.infoLog("This will send 200 response");
        return responseData;
    }
}
