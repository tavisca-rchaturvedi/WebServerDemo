package com.tavisca.workshops.responses;

import com.tavisca.workshops.RequestParser;
import com.tavisca.workshops.Responder;
import com.tavisca.workshops.ResponseCreator;
import com.tavisca.workshops.logger.Log;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CustomResourceFoundResponse implements Responder {

    public HashMap<String, String> nameToTagMap = new HashMap<>();
    public String customHTMLResponse = "";
    public String startHTMLResponse = "";
    public String endingHTMLResponse = "";
    

    public void resetParameters(){
        this.startHTMLResponse = "<html><body>";
        this.endingHTMLResponse = "</body></html>";
    }

    public CustomResourceFoundResponse(){
        try {
            this.startHTMLResponse = "<html><body>";
            this.endingHTMLResponse = "</body></html>";
            String[] lines = Files.readAllLines(new File("www/nameToTags.txt").toPath()).toArray(new String[0]);
            for (String line: lines) {
                String[] data = line.split(" ");
                nameToTagMap.put(data[0], data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getResponse(ResponseCreator responseCreator, RequestParser requestParser) {
        byte[] responseData = new byte[0];
        try {
            responseData = responseCreator.customFileResponse(processFile(new File(this.baseAddress + requestParser.getRequestURI().substring(1)))).getBytes();
            this.resetParameters();
            Log.warningLog(responseCreator.customFileResponse(processFile(new File(this.baseAddress + requestParser.getRequestURI().substring(1)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public String processFile(File customExtensionFile) throws IOException {
        List<String> lines =  Files.readAllLines(Paths.get(customExtensionFile.getPath()));
        Pattern tagContentPattern = Pattern.compile("(.*) is ([a-zA-Z]*)(.*)");
        Pattern numberAndTagPattern = Pattern.compile("([0-9]+) ([a-zA-Z]*)(.*)");

        for(String line : lines){
            Matcher matcher = tagContentPattern.matcher(line);
            Matcher matcher1 = numberAndTagPattern.matcher(line);
            if(matcher.find()){
                String tag = "", content = "";
                tag = this.nameToTagMap.get(matcher.group(1));
                content = matcher.group(2);
                if(line.endsWith(".")){
                    this.startHTMLResponse += "<" + tag + ">" + content + "</" + tag + ">";
                }
                else{
                    this.startHTMLResponse += "<" + tag + ">" + content;
                    this.endingHTMLResponse = "</" + tag + ">" + this.endingHTMLResponse;
                }
            }
            else if(matcher1.find()){
                int number = 0; String tag = "";
                number = Integer.parseInt(matcher1.group(1));
                tag = this.nameToTagMap.get(matcher1.group(2));
                for(int i = 0; i < number; i++){
                    if(line.endsWith(".")){
                        this.startHTMLResponse += "<" + tag + "></" + tag + ">";
                    }
                    else{
                        this.startHTMLResponse += "<" + tag + ">";
                        this.endingHTMLResponse = "</" + tag + ">" + this.endingHTMLResponse;
                    }
                }
            }

        }
        return this.startHTMLResponse+this.endingHTMLResponse;
    }
}
