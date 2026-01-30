package com.webapp.controllers;

import com.db_support.repositories.CompanyRepository;
import com.db_support.repositories.CountryRepository;
import com.metrics.MetricsHelper;
import com.utils.CurrencyFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
class WebController {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final CurrencyFormatter formatter;
    private final MetricsHelper metricsHelper;

    WebController(){
        companyRepository = new CompanyRepository();
        countryRepository = new CountryRepository();
        formatter = new CurrencyFormatter();
        metricsHelper = MetricsHelper.getMetricsHelper();
    }

    @GetMapping("/")
    public RedirectView Root(){
        return new RedirectView("/companies");
    }


    @GetMapping("/companies")
    public String Home(Model model){
        metricsHelper.markWebMeter();
        var companies = companyRepository.getCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("formatter", formatter);
        return "companies";
    }

    @GetMapping("/company/{id}")
    public String CompanyDetails(@PathVariable long id, Model model){
        metricsHelper.markWebMeter();
        var company = companyRepository.getCompany(id);
        if(company != null){
            model.addAttribute("company", company);
            model.addAttribute("formatter", formatter);
            return "details";
        }else{
            throw new ResponseStatusException(NOT_FOUND, "Resource Not Found");
        }
    }

    @GetMapping("/company/search/")
    public String CompanySearch(@RequestParam String searchTerm, Model model){
        metricsHelper.markWebMeter();
        searchTerm=searchTerm.replaceAll("[^A-Za-z]", "");
        var companies = companyRepository.searchCompany(searchTerm);
        model.addAttribute("companies", companies);
        model.addAttribute("formatter", formatter);
        return "companies";
    }

    @GetMapping("/countries")
    public String Countries(Model model){
        metricsHelper.markWebMeter();
        var countries = countryRepository.getCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("formatter", formatter);
        return "countries";
    }

    @GetMapping("/country/search/")
    public String CountrySearch(@RequestParam String searchTerm, Model model){
        metricsHelper.markWebMeter();
        searchTerm=searchTerm.replaceAll("[^A-Za-z]", "");
        var countries = countryRepository.searchCountry(searchTerm);
        model.addAttribute("countries", countries);
        model.addAttribute("formatter", formatter);
        return "countries";
    }

    @GetMapping("/country/{id}")
    public String CountryDetails(@PathVariable long id, Model model){
        metricsHelper.markWebMeter();
        var country = countryRepository.getCountry(id);
        if(country != null){
            var companies = companyRepository.getCompaniesByCountry(country.getCountry());
            model.addAttribute("companies", companies);
            model.addAttribute("formatter", formatter);
            model.addAttribute("heading", "Largest companies by market cap in "+country.getCountry());
            return "companies";
        }else{
            throw new ResponseStatusException(NOT_FOUND, "Resource Not Found");
        }
    }
}