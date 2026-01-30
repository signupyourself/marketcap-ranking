package com.collector.services;

import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectorService {

    private static final Logger log = LoggerFactory.getLogger(CollectorService.class);
    CompanyRepository companyRepository;
    CountryRepository countryRepository;
    String queue;


    public CollectorService(CompanyRepository companyRepository, CountryRepository countryRepository, String queue){
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.queue = queue;
    }

    public void collect(){

        try {
            RabbitMQHelper rabbitMQHelper = new RabbitMQHelper(queue);
            fetch();
            rabbitMQHelper.publish("Done");
            rabbitMQHelper.closeConnection();

        } catch (Exception e) {
            log.error("Error: ", e);
            System.exit(-1);
        }
    }

    public void fetch(){

        companyRepository.deleteCompanies();
        countryRepository.deleteCountries();

        WebService webService = new WebService(
                new HttpWebClient(),
                new CompanyParser(),
                "https://companiesmarketcap.com/"
        );

        log.info("Started fetching data");

        var companies = webService.fetch();

        log.info("Fetching data done");

        for(var company: companies){
            companyRepository.insertCompany(company);
        }
    }
}
