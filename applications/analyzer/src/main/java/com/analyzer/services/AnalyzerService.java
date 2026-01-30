package com.analyzer.services;

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

public class AnalyzerService {

    private static final Logger log = LoggerFactory.getLogger(AnalyzerService.class);
    private final RabbitMQHelper rabbitMQHelper;
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    public AnalyzerService(CompanyRepository companyRepository,
                           CountryRepository countryRepository, String queue) {
        try{
            rabbitMQHelper = new RabbitMQHelper(queue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    public void analyze(){

        //rabbitmq handler
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            if(message.equals("Done")){
                rank();
            }
        };

        try{
            rabbitMQHelper.listen(deliverCallback);
        } catch (Exception e) {
            log.error("Error : ", e);
            System.exit(-1);
        }
    }

    public void rank(){

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
