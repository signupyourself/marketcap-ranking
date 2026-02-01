package com.analyzer.test;

import com.analyzer.services.AnalyzerService;
import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.collector.services.CollectorService;
import com.collector.services.WebService;
import com.db_support.connection.DBConnection;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq_support.RabbitMQHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


public class CollectorAnalyzerIntegrationTest {

    private static CompanyRepository companyRepository;
    private static CountryRepository countryRepository;


    @BeforeAll
    public static void setUp() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {

//        DBConnection dbConnection = DBConnection.getDbConnection(
//                "jdbc:postgresql://localhost:5433/Test",
//                "admin",
//                "1234"
//        );
        DBConnection dbConnection = DBConnection.getDbConnection();
        companyRepository = new CompanyRepository(DBConnection.getDbConnection());
        countryRepository = new CountryRepository(DBConnection.getDbConnection());
    }

    @AfterAll
    public static void tearDown(){
//        companyRepository.deleteCompanies();
//        countryRepository.deleteCountries();
    }


    @Test
    public void testAnalyzerStartsAfterCollectorUpdatesTheMessageQueue() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {

        CollectorService collectorService = new CollectorService(
                new CompanyRepository(),
                new CountryRepository(),
                new RabbitMQHelper("COLLECTOR_ANALYZER_TEST"),
                new WebService(
                        new HttpWebClient(),
                        new CompanyParser(),
                        "https://companiesmarketcap.com/"
                )
        );

        collectorService.collect();

        AnalyzerService analyzerService = new AnalyzerService(
                new CompanyRepository(),
                new CountryRepository(),
                new RabbitMQHelper("COLLECTOR_ANALYZER_TEST")
        );

        analyzerService.analyze();

    }
}
