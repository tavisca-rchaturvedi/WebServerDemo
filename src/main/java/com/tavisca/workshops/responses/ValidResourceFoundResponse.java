package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ValidResourceFoundResponse implements Responder {

    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        Log.getLogger().log(Level.INFO,"File exists with file name " + requestParser.getRequestURI().substring(1));
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + requestParser.getRequestURI().substring(1))).getBytes();
        Log.getLogger().log(Level.INFO,responseCreator.fileResponse(new File(this.baseAddress + requestParser.getRequestURI().substring(1))));
        return responseData;
    }
}
