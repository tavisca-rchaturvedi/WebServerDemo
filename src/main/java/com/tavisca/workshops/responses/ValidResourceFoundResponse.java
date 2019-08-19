package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;

public class ValidResourceFoundResponse implements Responder {

    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        Log.infoLog("File exists with file name " + requestParser.getRequestURI().substring(1));
        byte[] responseData = responseCreator.fileResponse(new File(this.baseAddress + requestParser.getRequestURI().substring(1))).getBytes();
        Log.infoLog(responseCreator.fileResponse(new File(this.baseAddress + requestParser.getRequestURI().substring(1))));
        return responseData;
    }
}
