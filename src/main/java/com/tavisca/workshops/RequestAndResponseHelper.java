package com.tavisca.workshops;
import com.tavisca.workshops.responses.*;
import java.util.HashMap;
import java.util.Map;

public class RequestAndResponseHelper {

    public static Map<Boolean, Responder> resourceTypeToResponderMap = new HashMap<Boolean, Responder>(){{
        put(true,new RootURIResponse());
        put(false, new ResourceRequestedResponse());
    }};

    public byte[] processAndCreateResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = new byte[0];
        if(requestParser.getMethod().equals("GET")){
            responseData = resourceTypeToResponderMap.get(requestParser.getRequestURI().trim().equals("/")).getResponse(responseCreator, requestParser);
        }
        return responseData;
    }
}
