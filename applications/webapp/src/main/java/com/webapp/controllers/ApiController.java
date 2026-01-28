package com.webapp.controllers;

import com.db_support.models.Company;
import com.db_support.models.Country;
import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private CompanyRepository companyRepository = new CompanyRepository();
    private CountryRepository countryRepository = new CountryRepository();

    @GetMapping("/api/companies")
    public List<Company> Companies(){
        var companies = companyRepository.getCompanies();
        return companies;
    }

    @GetMapping("/api/countries")
    public List<Country> Countries(){
        var countries = countryRepository.getCountries();
        return countries;
    }
}
