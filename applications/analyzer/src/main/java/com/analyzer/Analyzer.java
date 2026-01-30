package com.analyzer;

import com.analyzer.ranker.CompanyRanker;
import com.analyzer.ranker.CountryRanker;
import com.analyzer.services.AnalyzerService;
import com.db_support.models.Company;
import com.db_support.models.Country;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq_support.RabbitMQHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Analyzer{

    private static final Logger log = LoggerFactory.getLogger(Analyzer.class);
    private static RabbitMQHelper rabbitMQHelper;

    public static void main(String[] args) {

        AnalyzerService analyzerService = new AnalyzerService(
                new CompanyRepository(),
                new CountryRepository(),
                "COLLECTOR_ANALYZER_QUEUE");
        analyzerService.analyze();

    }

}