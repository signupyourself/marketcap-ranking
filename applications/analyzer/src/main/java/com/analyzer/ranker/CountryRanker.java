package com.analyzer.ranker;

import com.db_support.models.Company;
import com.db_support.models.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryRanker {

    public static List<Country> rank(List<Company> companies){
        List<Country> countries = findCountries(companies);
        ranker(countries);
        return countries;
    }

    private static List<Country> findCountries(List<Company> companies){
        Map<String, Country> countryHashMap = new HashMap<>();
        for(var company: companies){
            if(countryHashMap.get(company.getCountry()) == null){
                var country = new Country();
                country.setCountry(company.getCountry());
                country.setMarketCap(company.getMarketCap());
                country.setRevenue(company.getRevenue());
                country.setEarning(company.getEarning());
                countryHashMap.put(company.getCountry(), country);
            }else {
                var country = countryHashMap.get(company.getCountry());
                country.setEarning(country.getEarning()+company.getEarning());
                country.setRevenue(country.getRevenue()+company.getRevenue());
                country.setMarketCap(country.getMarketCap()+company.getMarketCap());
            }
        }
        List<Country> countries = new ArrayList<>();
        for(var key: countryHashMap.keySet()){
            countries.add(countryHashMap.get(key));
        }
        return countries;
    }

    private static void ranker(List<Country> countries){
        int outerIndex=0;
        int innderIndex;
        while (outerIndex<countries.size()){
            innderIndex=outerIndex+1;
            int largest=outerIndex;
            while (innderIndex<countries.size()){
                if(countries.get(innderIndex).getMarketCap()>countries.get(largest).getMarketCap()) {
                    largest = innderIndex;
                }
                innderIndex++;
            }
            if(largest != outerIndex){
                Country temp = countries.get(outerIndex);
                countries.set(outerIndex, countries.get(largest));
                countries.set(largest, temp);
            }
            countries.get(outerIndex).setMarketCapRanking(outerIndex+1);
            outerIndex++;
        }
    }
}
