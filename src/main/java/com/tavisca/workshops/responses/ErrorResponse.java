package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;

public class ErrorResponse implements Responder {
    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        Log.warningLog("File does not exist");
        byte[] responseData = responseCreator.method404(new File(this.baseAddress + "404.html")).getBytes();
        Log.warningLog(responseCreator.method404(new File(this.baseAddress + "404.html")));
        return responseData;
    }
}
