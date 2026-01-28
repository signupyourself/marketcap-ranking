package com.collector.httpclients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpWebClient {

    public String get(String uri){
        String responseBody="";
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            responseBody = response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }
}