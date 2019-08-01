package com.tavisca.workshops;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ResponseTests {

    @Test
    public void resourceFoundTest(){
        String[] expectedResponse = ("HTTP/1.1 200 OK\n" +
                "Server: My Java HTTP Server: 1.0\n" +
                "Date: Thu Aug 01 10:59:49 IST 2019\n" +
                "Content-Type: text/html\n" +
                "Content-length: 54\n" +
                "\n" +
                "<html><body><H1>Hello! How are you?</H1></body></html>").split("\n");

        ResponseCreator responseCreator = new ResponseCreator();
        String[] response = responseCreator.fileResponse(new File("www/hello.html")).split("\r\n");

        for(int i = 0; i < expectedResponse.length; i++){
            if(i != 2){
                assertEquals(expectedResponse[i],response[i]);
            }
            else{
                assertNotEquals(expectedResponse[i],response[i]);
            }
        }
    }

    @Test
    public void resourceNotFoundTest(){
        String[] expectedResponse = ("HTTP/1.1 404\n" +
                "Server: My Java HTTP Server: 1.0\n" +
                "Date: Thu Aug 01 11:10:59 IST 2019\n" +
                "Content-Type: text/html\n" +
                "Content-length: 51\n" +
                "\n" +
                "<html><body><h1>Page not found!!</h1></body></html>").split("\n");

        ResponseCreator responseCreator = new ResponseCreator();
        String[] response = responseCreator.method404(new File("www/404.html")).split("\r\n");

        for(int i = 0; i < expectedResponse.length; i++){
            if(i != 2){
                assertEquals(expectedResponse[i],response[i]);
            }
            else{
                assertNotEquals(expectedResponse[i],response[i]);
            }
        }
    }

    @Test
    public void rootRequestResponse(){
        String[] expectedResponse = ("HTTP/1.1 200 OK\n" +
                "Server: My Java HTTP Server: 1.0\n" +
                "Date: Thu Aug 01 11:14:38 IST 2019\n" +
                "Content-Type: text/html\n" +
                "Content-length: 147\n" +
                "\n" +
                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body><H1>You are successfully connected!!</H1></body></html>").split("\n");

        ResponseCreator responseCreator = new ResponseCreator();
        String[] response = responseCreator.fileResponse(new File("www/OkResponse.html")).split("\r\n");

        for(int i = 0; i < expectedResponse.length; i++){
            if(i != 2){
                assertEquals(expectedResponse[i],response[i]);
            }
            else{
                assertNotEquals(expectedResponse[i],response[i]);
            }
        }
    }
}
