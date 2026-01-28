package com.collector.integration_tests;

import com.collector.parsers.CollectorService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    public void firstTest(){
        CollectorService collectorService = new CollectorService(companyRepository, countryRepository);
        collectorService.collect();
        var companies = companyRepository.getCompanies();
        assertEquals(100, companies.size());
    }
}
