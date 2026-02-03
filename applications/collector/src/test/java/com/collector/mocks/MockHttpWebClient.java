package com.collector.mocks;

import com.collector.httpclients.HttpWebClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MockHttpWebClient extends HttpWebClient {

    public String get(String url){
        if(url.endsWith("com/")){
            return homPage();
        }else if(url.endsWith("revenue/")){
            return revenuePage();
        }else if(url.endsWith("earnings/")){
            return earningsPage();
        }else{
            return "";
        }
    }

    private String revenuePage() {
        String buffer = null;
        try{
            InputStream stream = getClass().getClassLoader().getResourceAsStream("revenue.html");
            if(stream == null){
                throw new FileNotFoundException();
            }
            int ch;
            while ((ch = stream.read()) != -1) {
                buffer+=(char)ch;
            }
            stream.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String homPage() {
        String buffer = null;
        try{
            InputStream stream = getClass().getClassLoader().getResourceAsStream("home.html");
            if(stream == null){
                throw new FileNotFoundException();
            }
            int ch;
            while ((ch = stream.read()) != -1) {
                buffer+=(char)ch;
            }
            stream.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String earningsPage(){
        String buffer = null;
        try{
            InputStream stream = getClass().getClassLoader().getResourceAsStream("earnings.html");
            if(stream == null){
                throw new FileNotFoundException();
            }
            int ch;
            while ((ch = stream.read()) != -1) {
                buffer+=(char)ch;
            }
            stream.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
