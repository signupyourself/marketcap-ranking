package com.collector.integration_tests;

import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.collector.services.CollectorService;
import com.collector.services.WebService;
import com.db_support.connection.DBConnection;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq_support.RabbitMQHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectorIntegrationTest {

    private static CompanyRepository companyRepository;
    private static CountryRepository countryRepository;

    @BeforeAll
    public static void setUp() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        DBConnection dbConnection = DBConnection.getDbConnection(
                "jdbc:postgresql://localhost:5433/Test",
                "admin",
                "1234"
                );
        companyRepository = new CompanyRepository(dbConnection);
        countryRepository = new CountryRepository(dbConnection);
    }

    @AfterAll
    public static void tearDown() throws IOException, TimeoutException {
        countryRepository.deleteCountries();
        companyRepository.deleteCompanies();
    }

    @Test
    public void testCollectorFetchesDataAndSavesIntoTheDB() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        CollectorService collectorService = new CollectorService(
                companyRepository,
                countryRepository,
                new RabbitMQHelper("COLLECTOR_ANALYZER_TEST"),
                new WebService(
                        new HttpWebClient(),
                        new CompanyParser(),
                        "https://companiesmarketcap.com/"
                )
        );
        collectorService.collect();
        var companies = companyRepository.getCompanies();
        assertEquals(100, companies.size());
    }

    @Test
    public void testCollectorUpdatesTheMessageQueueAfterCollectingData() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        CollectorService collectorService = new CollectorService(
                companyRepository,
                countryRepository,
                new RabbitMQHelper("COLLECTOR_ANALYZER_T"),
                new WebService(
                        new HttpWebClient(),
                        new CompanyParser(),
                        "https://companiesmarketcap.com/"
                )
        );
        collectorService.collect();
        DeliverCallback callback = (string, delivery)->{
            String output = new String(delivery.getBody(), StandardCharsets.UTF_8);
            assertEquals("Not Done", output);
        };
        RabbitMQHelper helper = new RabbitMQHelper("COLLECTOR_ANALYZER_T");
        DeliverCallback deliverCallback = (string, delivery)->{
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            assertEquals("Done", message);
        };
        helper.listen(deliverCallback);
    }
}
