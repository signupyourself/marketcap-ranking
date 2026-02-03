package com.analyzer;

import com.analyzer.services.AnalyzerService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Analyzer{

    private static final Logger log = LoggerFactory.getLogger(Analyzer.class);
    public static void main(String[] args) {

        try {
            AnalyzerService analyzerService = new AnalyzerService(
                    new CompanyRepository(),
                    new CountryRepository(),
                    new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE"));
            analyzerService.analyze();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}