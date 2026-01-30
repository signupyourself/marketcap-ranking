package com.rabbitmq_support;

import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RabbitMQHelperTest {

    private static RabbitMQHelper rabbitMQHelper;
    private static final String TEST_MESSAGE = "test message";

    @BeforeAll
    public static void setUp(){
        try {
            rabbitMQHelper = new RabbitMQHelper("COLLECTOR_ANALYZER_TEST_QUEUE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testPublish(){
        try {
            DeliverCallback callback = (string, delivery)->{
                String message = new String(delivery.getBody(), "UTF-8");
                assertEquals(TEST_MESSAGE, message);
            };


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
