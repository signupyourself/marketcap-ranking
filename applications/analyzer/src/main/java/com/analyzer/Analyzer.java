package com.analyzer;

import com.analyzer.ranker.CompanyRanker;
import com.analyzer.ranker.CountryRanker;
import com.db_support.models.Company;
import com.db_support.models.Country;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq_support.RabbitMQHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Analyzer{

    private static final Logger log = LoggerFactory.getLogger(Analyzer.class);
    private static RabbitMQHelper rabbitMQHelper;

    public static void main(String[] args) {

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                if(message.equals("Done")){
                    startApp();
                }
        };

        try{
            rabbitMQHelper = new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE");
            rabbitMQHelper.listen(deliverCallback);
        } catch (Exception e) {
            log.error("Error: ", e);
            System.exit(-1);
        }

    }

    public static void startApp(){

        CompanyRepository companyRepository = new CompanyRepository();
        CountryRepository countryRepository = new CountryRepository();

        List<Company> companies = companyRepository.getCompanies();

        CompanyRanker.rank(companies);
        for (var company : companies) {
            companyRepository.updateMarketCapRanking(company);
        }
        List<Country> countries = CountryRanker.rank(companies);
        for (var country : countries) {
            countryRepository.insertCountry(country);
        }
        try {
            rabbitMQHelper.closeConnection();
        } catch (Exception e) {
            log.error("Error: ", e);
            System.exit(-1);
        }

    }

}