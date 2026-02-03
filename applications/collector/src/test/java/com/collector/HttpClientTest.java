package com.collector;

import com.collector.httpclients.HttpWebClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class  HttpClientTest {

    @Test
    public void testGetReturnsNonNullResponseWhenValidURLIsTheInput(){
        HttpWebClient webClient = new HttpWebClient();
        String resp = webClient.get("https://www.google.com");
        assert(!resp.isEmpty());
    }

    @Test
    public void testGetReturnsNullResponseWhenInvalidURLIsTheInput(){
        String resp="";
        try{
            HttpWebClient webClient = new HttpWebClient();
            resp = webClient.get("https://www.google.com");
            assert(!resp.isEmpty());
        } catch (Exception e) {
            assertNull(resp);
        }

    }

}
