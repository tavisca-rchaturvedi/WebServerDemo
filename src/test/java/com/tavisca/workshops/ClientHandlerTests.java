package com.tavisca.workshops;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;
import static org.junit.Assert.assertNotEquals;



public class ClientHandlerTests {
    @Test
    public void outputStreamTest(){
        byte[] responseData = new byte[]{10,20,30,40,50};
        MockSocket mockSocket = new MockSocket();
        OutputStream outputStream = mockSocket.getOutputStream();
        ClientHandler clientHandler = new ClientHandler();
        try {
            clientHandler.respondToClient(responseData,outputStream);
            byte[] output = mockSocket.output();
            for(int i = 0; i < responseData.length; i++){
                assertEquals(responseData[i],output[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void prepareResponseFromRequestTest(){
        String[] expectedResponseData = ("HTTP/1.1 200 OK\r\n" +
                                    "Server: My Java HTTP Server: 1.0\r\n" +
                                    "Date: Thu Aug 01 11:14:38 IST 2019\r\n" +
                                    "Content-Type: text/html\r\n" +
                                    "Content-length: 147\r\n"  +
                                    "\r\n" +
                                    "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body><H1>You are successfully connected!!</H1></body></html>").split("\r\n");

        try {
            MockSocket mockSocket = new MockSocket();


            BufferedReader reader = new BufferedReader(new InputStreamReader(mockSocket.getInputStream()));
            ClientHandler clientHandler = new ClientHandler();
            String[] responseData = new String(clientHandler.prepareResponseFromRequest(reader, mockSocket)).split("\r\n");
            for(int i = 0; i < expectedResponseData.length; i++){
                if(expectedResponseData[i].contains("Date:")){
                    assertNotEquals(expectedResponseData[i],responseData[i]);
                }
                else{
                    assertEquals(expectedResponseData[i],responseData[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
