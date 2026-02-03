package com.rabbitmq_support;

import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RabbitMQHelperTest {

    private static final String TEST_MESSAGE = "test message";

    @BeforeAll
    public static void setUp(){
        try {
            RabbitMQHelper rabbitMQHelper = new RabbitMQHelper("COLLECTOR_ANALYZER_TEST");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testPublish(){
        try {
            DeliverCallback callback = (string, delivery)->{
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                assertEquals(TEST_MESSAGE, message);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
