package com.tavisca.workshops;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class MockSocket extends Socket {

    private final List<Byte> bytesList = new ArrayList<>();
    public InputStream getInputStream(){
        return new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    }
    public OutputStream getOutputStream(){
        return new OutputStream() {
            @Override
            public void write(int b) {
                bytesList.add((byte)b);
            }
        };
    }

    public byte[] output(){
        byte[] array = new byte[bytesList.size()];
        for(int i = 0; i < bytesList.size(); i++){
            array[i] = bytesList.get(i);
        }
        return array;
    }

}
