package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class RootURIResponse implements Responder {
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + "OkResponse.html")).getBytes();
        Log.getLogger().log(Level.INFO,responseCreator.fileResponse(new File(this.baseAddress + "OkResponse.html")));
        Log.getLogger().log(Level.INFO,"This will send 200 response");
        return responseData;
    }
}
