package com.webapp.controllers;

import com.db_support.models.Company;
import com.db_support.models.Country;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.metrics.MetricsHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final MetricsHelper metricsHelper;

    ApiController(){
        companyRepository = new CompanyRepository();
        countryRepository = new CountryRepository();
        metricsHelper = MetricsHelper.getMetricsHelper();
    }
    @GetMapping("/api/companies")
    public List<Company> Companies(){
        metricsHelper.markApiMeter();
        return companyRepository.getCompanies();
    }

    @GetMapping("/api/countries")
    public List<Country> Countries(){
        metricsHelper.markApiMeter();
        return countryRepository.getCountries();
    }
}
