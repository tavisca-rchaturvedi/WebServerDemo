package com.tavisca.workshops;

public class RequestParser {

    private String method = "";
    private String requestURI = "";
    private String httpVersion = "";


    public RequestParser(String request){
        if(request != null && !request.isEmpty()){
            String[] partsOfString = request.split(" ");
            this.method = partsOfString[0];
            this.requestURI = partsOfString[1];
            this.httpVersion = partsOfString[2];
        }
    }

    public String getMethod(){
        return this.method;
    }

    public String getRequestURI(){
        return this.requestURI;
    }

    public String getHttpVersion(){
        return this.httpVersion;
    }


}
