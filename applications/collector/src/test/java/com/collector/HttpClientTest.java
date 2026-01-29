package com.collector;

import com.collector.httpclients.HttpWebClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class  HttpClientTest {

    @Test
    public void testGetReturnsValidResponseBodyWhenValidURlIsTheInput(){
        HttpWebClient webClient = new HttpWebClient();
        String resp = webClient.get("https://www.google.com");
        assert(!resp.isEmpty());
    }

}
