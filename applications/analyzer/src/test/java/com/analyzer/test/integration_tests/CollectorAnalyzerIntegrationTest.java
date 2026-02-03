package com.analyzer.test.integration_tests;

import com.analyzer.services.AnalyzerService;
import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.collector.services.CollectorService;
import com.collector.services.WebService;
import com.db_support.connection.DBConnection;
import com.db_support.models.Company;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CollectorAnalyzerIntegrationTest {

    private static CompanyRepository companyRepository;
    private static CountryRepository countryRepository;

    @BeforeAll
    public static void setUp() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        DBConnection dbConnection = DBConnection.getDbConnection("jdbc:postgresql://localhost:5433/Test", "admin", "1234");
        companyRepository = new CompanyRepository(dbConnection);
        countryRepository = new CountryRepository(dbConnection);
        Company company = new Company();
        company.setName("NVIDIA");
        company.setCountry("USA");
        company.setImageUrl("http://imageUrl");
        company.setMarketCap(4555.0);
        company.setEarning(50.00);
        company.setDescription("Semiconductor");
        company.setMarketCapRanking(1);
        companyRepository.insertCompany(company);

    }

    @AfterAll
    public static void tearDown(){
        countryRepository.deleteCountries();
        companyRepository.deleteCompanies();
    }
    @Test
    public void testAnalyzerUpdatesTheCountryTableAfterCollectorFinishesExecution() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException, InterruptedException {
        CollectorService collectorService = new CollectorService(
                companyRepository,
                countryRepository,
                new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE_TEST"),
                new WebService(
                        new HttpWebClient(),
                        new CompanyParser(),
                        "https://companiesmarketcap.com/"
                )
        );

        AnalyzerService analyzerService = new AnalyzerService(
                companyRepository,
                countryRepository,
                new RabbitMQHelper("COLLECTOR_ANALYZER_QUEUE_TEST")
        );
        collectorService.collect();
        analyzerService.analyze();
        Thread.sleep(10000);
        assertNotEquals(0, countryRepository.getCountries().size());
    }
}
