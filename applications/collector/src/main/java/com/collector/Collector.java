package com.collector;


import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.collector.services.CollectorService;
import com.collector.services.WebService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Collector{


    public static void main(String []args){
        try{
            CollectorService collectorService = new CollectorService(
                    new CompanyRepository(),
                    new CountryRepository(),
                    new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE"),
                    new WebService(new HttpWebClient(), new CompanyParser(), "https://companiesmarketcap.com/")
            );
            collectorService.collect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}