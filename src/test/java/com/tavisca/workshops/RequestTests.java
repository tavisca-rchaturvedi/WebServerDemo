package com.tavisca.workshops;

import org.junit.Test;
import static org.junit.Assert.*;
public class RequestTests {

    @Test
    public void methodNameOnARequest(){
        String request = "GET / /http/1.1";
        RequestParser requestParser = new RequestParser(request);
        assertEquals("GET", requestParser.getMethod());
    }

    @Test
    public void httpVersionOnARequest(){
        String request = "GET / /http/1.1";
        RequestParser requestParser = new RequestParser(request);
        assertEquals("/http/1.1", requestParser.getHttpVersion());
    }

    @Test
    public void uriOnARequest(){
        String request = "GET / /http/1.1";
        RequestParser requestParser = new RequestParser(request);
        assertEquals("/", requestParser.getRequestURI());
    }
}
