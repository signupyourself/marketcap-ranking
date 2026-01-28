package com.collector;
import com.collector.parsers.CollectorService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;

public class Collector{
    public static void main(String args[]){
        try {
            RabbitMQHelper rabbitMQHelper = new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE");
            CollectorService collectorService = new CollectorService(
                    new CompanyRepository(),
                    new CountryRepository());
            collectorService.collect();
            rabbitMQHelper.publish("Done");
            rabbitMQHelper.closeConnection();

        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
    }
}