package com.analyzer.test;

import com.analyzer.ranker.CountryRanker;
import com.db_support.models.Company;
import com.db_support.models.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryRankerTest {

    private static List<Company> companies = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        for(int i=5; i>0; i--){
            var company = new Company();
            company.setName("Company"+i);
            company.setCountry("Company "+i+" country");
            company.setDescription("Company "+i+" description");
            company.setImageUrl("Company "+i+" imageUrl");
            company.setMarketCap(i);
            company.setEarning(i);
            company.setRevenue(i);
            companies.add(company);
        }
    }

    @Test
    public void rankerTest(){
        var countries = CountryRanker.rank(companies);
        assertEquals(5, countries.size());
    }
}
