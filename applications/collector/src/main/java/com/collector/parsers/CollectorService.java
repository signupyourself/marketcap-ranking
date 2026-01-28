package com.collector.parsers;

import com.collector.httpclients.HttpWebClient;
import com.collector.services.WebService;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;

public class CollectorService {

    CompanyRepository companyRepository;
    CountryRepository countryRepository;

    public CollectorService(CompanyRepository companyRepository, CountryRepository countryRepository){
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    public void collect(){



        companyRepository.deleteCompanies();
        countryRepository.deleteCountries();

        WebService webService = new WebService(
                new HttpWebClient(),
                new CompanyParser(),
                "https://companiesmarketcap.com/"
        );

        System.out.println("Fetching data");

        var companies = webService.fetch();

        System.out.println("Fetching done");

        for(var company: companies){
            companyRepository.insertCompany(company);
        }

    }
}
