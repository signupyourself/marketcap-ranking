package com.rabbitmq_support;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQHelper {

    Channel channel;
    Connection connection;
    String queue;

    public RabbitMQHelper(String queue) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {

        this.queue = queue;
        String uri = System.getenv("RABBITMQ_URL");
        if (uri == null) {
            uri = "amqp://guest:guest@localhost";
        }

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri(uri);
        factory.setRequestedHeartbeat(30);
        factory.setConnectionTimeout(30);
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queue, false, false, false, null);

    }

    public void publish(String message) throws IOException {
        channel.basicPublish("", queue, null, message.getBytes());
    }

    public void listen(DeliverCallback deliverCallback) throws IOException {
        channel.basicConsume(queue, true, deliverCallback, tag->{});
    }

    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}