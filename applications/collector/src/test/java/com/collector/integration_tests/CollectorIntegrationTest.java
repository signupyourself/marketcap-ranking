package com.collector.integration_tests;

import com.collector.services.CollectorService;
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
    public static void Test(){
            companyRepository = new CompanyRepository(
                    "jdbc:postgresql://localhost:5433/Test",
                    "admin",
                    "1854"
            );
            countryRepository = new CountryRepository(
                    "jdbc:postgresql://localhost:5433/Test",
                    "admin",
                    "1854"
            );
    }

    @AfterAll
    public static void cleanUp(){
        countryRepository.deleteCountries();
        companyRepository.deleteCompanies();
    }

    @Test
    public void testCollectorFetchesDataAndSavesIntoTheDB(){
        CollectorService collectorService = new CollectorService(companyRepository, countryRepository, "COLLECTOR_ANALYZER_TEST");
        collectorService.collect();
        var companies = companyRepository.getCompanies();
        assertEquals(100, companies.size());
    }

    @Test
    public void testCollectorUpdatesTheMessageQueueAfterCollectingData() throws URISyntaxException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        RabbitMQHelper helper = new RabbitMQHelper("COLLECTOR_ANALYZER_TEST");
        CollectorService collectorService = new CollectorService(companyRepository, countryRepository, "COLLECTOR_ANALYZER_TEST");
        collectorService.collect();
        DeliverCallback callback = (string, delivery)->{
            String output = new String(delivery.getBody(), StandardCharsets.UTF_8);
            assertEquals("Done", output);
        };
        helper.listen(callback);
        helper.closeConnection();
    }
}
