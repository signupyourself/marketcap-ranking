package com.collector.services;

import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.db_support.models.Company;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CollectorService {

    private static final Logger log = LoggerFactory.getLogger(CollectorService.class);
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final RabbitMQHelper rabbitMQHelper;
    private final WebService webService;


    public CollectorService(CompanyRepository companyRepository, CountryRepository countryRepository, RabbitMQHelper rabbitMQHelper, WebService webService){
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.rabbitMQHelper = rabbitMQHelper;
        this.webService = webService;
    }

    public void collect(){

        fetch();
        try {
            rabbitMQHelper.publish("Done");
            rabbitMQHelper.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fetch(){

        companyRepository.deleteCompanies();
        countryRepository.deleteCountries();


        log.info("Started fetching data");

        List<Company> companies = webService.fetch();

        log.info("Fetching data done");

        for(Company company: companies){
            companyRepository.insertCompany(company);
        }
    }
}
