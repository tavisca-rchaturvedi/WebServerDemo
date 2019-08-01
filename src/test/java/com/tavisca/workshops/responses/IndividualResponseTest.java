package com.tavisca.workshops.responses;

import com.tavisca.workshops.RequestAndResponseHelper;
import com.tavisca.workshops.RequestParser;
import com.tavisca.workshops.ResponseCreator;
import org.junit.Test;

import static org.junit.Assert.*;


public class IndividualResponseTest {
    @Test
    public void errorResponseTest(){
        String request = "GET /hell HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 404\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 11:10:59 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 51\r\n" +
                "\r\n" +
                "<html><body><h1>Page not found!!</h1></body></html>").split("\r\n");
        ErrorResponse errorResponse = new ErrorResponse();
        String[] responseData = new String(errorResponse.getResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }

    @Test
    public void okResponseTest(){
        String request = "GET / HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 200 OK\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 11:14:38 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 147\r\n" +
                "\r\n" +
                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body><H1>You are successfully connected!!</H1></body></html>").split("\r\n");
        RootURIResponse rootURIResponse = new RootURIResponse();
        String[] responseData = new String(rootURIResponse.getResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        System.out.println(responseData[0]);

        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }


    @Test
    public void ResourceFoundResponseTest(){
        String request = "GET /hello.html HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 200 OK\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 10:59:49 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 54\r\n" +
                "\r\n" +
                "<html><body><H1>Hello! How are you?</H1></body></html>").split("\r\n");
        ValidResourceFoundResponse validResourceFoundResponse = new ValidResourceFoundResponse();
        String[] responseData = new String(validResourceFoundResponse.getResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }

    @Test
    public void genericResponseForResourceFoundTest(){
        String request = "GET /hello.html HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 200 OK\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 10:59:49 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 54\r\n" +
                "\r\n" +
                "<html><body><H1>Hello! How are you?</H1></body></html>").split("\r\n");
        RequestAndResponseHelper helper = new RequestAndResponseHelper();
        String[] responseData = new String(helper.processAndCreateResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }

    @Test
    public void genericResponseForRootTest(){
        String request = "GET / HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 200 OK\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 11:14:38 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 147\r\n" +
                "\r\n" +
                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body><H1>You are successfully connected!!</H1></body></html>").split("\r\n");
        RequestAndResponseHelper helper = new RequestAndResponseHelper();
        String[] responseData = new String(helper.processAndCreateResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        System.out.println(responseData[0]);

        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }

    @Test
    public void genericResponseForResourceNotFoundTest(){
        String request = "GET /hell HTTP/1.1";
        String[] expectedResponse =("HTTP/1.1 404\r\n" +
                "Server: My Java HTTP Server: 1.0\r\n" +
                "Date: Thu Aug 01 11:10:59 IST 2019\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: 51\r\n" +
                "\r\n" +
                "<html><body><h1>Page not found!!</h1></body></html>").split("\r\n");
        RequestAndResponseHelper helper = new RequestAndResponseHelper();
        String[] responseData = new String(helper.processAndCreateResponse(new ResponseCreator(), new RequestParser(request))).split("\r\n");
        for(int i = 0; i < expectedResponse.length; i++){
            if(expectedResponse[i].contains("Date:")){
                assertNotEquals(expectedResponse[i],responseData[i]);
            }
            else{
                assertEquals(expectedResponse[i],responseData[i]);
            }
        }
    }



}
