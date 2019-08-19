package com.tavisca.workshops;

public interface Responder{
    String baseAddress = "resources/main";
    byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser);
}