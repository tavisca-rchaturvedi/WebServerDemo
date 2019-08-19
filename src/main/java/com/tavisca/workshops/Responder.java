package com.tavisca.workshops;

public interface Responder{
    String baseAddress = "www/";
    byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser);
}