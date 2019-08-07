package com.tavisca.workshops;

import java.io.IOException;

public interface Responder{
    public String baseAddress = "www/";
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser);
}