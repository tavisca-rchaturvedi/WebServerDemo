package com.tavisca.workshops;

public interface Responder{
    public String baseAddress = "www/";
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser);
}