package com.collector;


import com.collector.services.CollectorService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;

public class Collector{


    public static void main(String []args){
        CollectorService collectorService = new CollectorService(
                new CompanyRepository(),
                new CountryRepository(),
                "COLLECTOR_ANALYZER_QUEUE");
    }
}