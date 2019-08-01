package com.tavisca.workshops.responses;
import com.tavisca.workshops.*;
import com.tavisca.workshops.logger.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ErrorResponse implements Responder {
    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        Log.getLogger().log(Level.WARNING,"File does not exist");
        byte[] responseData = responseCreator.method404(new File(this.baseAddress + "404.html")).getBytes();
        Log.getLogger().log(Level.WARNING,responseCreator.method404(new File(this.baseAddress + "404.html")));
        return responseData;
    }
}
