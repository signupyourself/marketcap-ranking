package com.db_support.integration_tests;

import com.db_support.connection.DBConnection;
import com.db_support.models.Country;
import com.db_support.repositories.CountryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryRepoIntegrationTest {

    private static DBConnection dbConnection;
    private static CountryRepository countryRepository;

    @BeforeEach
    public  void setUp(){
        dbConnection = DBConnection.getDbConnection ("jdbc:postgresql://localhost:5433/Test", "admin", "1234");
        countryRepository = new CountryRepository(dbConnection);
        Country country = new Country();
        country.setCountry("USA");
        country.setMarketCap(1234.0);
        country.setRevenue(1235.0);
        country.setEarning(1236.0);
        country.setMarketCapRanking(1);
        countryRepository.insertCountry(country);
    }

    @AfterEach
    public  void tearDown(){
        try{
            dbConnection.execute("DELETE FROM Countries");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetCountriesReturnsListOfCountries(){
        var countries = countryRepository.getCountries();
        assertEquals(1,countries.size());
    }

    @Test
    public void testSearchCountryWithCountrySavedInDBReturnsNonEmptyList(){
        var countries = countryRepository.searchCountry("usa");
        assertEquals(1, countries.size());
    }

    @Test
    public void testSearchCountryWithCountryNotSavedInDBReturnsEmptyList(){
        var countries = countryRepository.searchCountry("Macedonia");
        assertEquals(0, countries.size());
    }

    @Test
    public void testSearchCountryWithQuotesRemovesQuotesAndReturnsSearchResult(){
        var countries = countryRepository.searchCountry("u'sa");
        assertEquals(0, countries.size());
    }
    @Test
    public void testDeleteCountriesDeletesAllRows(){
        countryRepository.deleteCountries();
        var countries = countryRepository.getCountries();
        assertEquals(0,countries.size());
    }

}
