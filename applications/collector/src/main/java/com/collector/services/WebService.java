package com.collector.services;

import com.collector.httpclients.HttpWebClient;
import com.collector.models.CompanyDao;
import com.collector.parsers.CompanyParser;
import com.db_support.models.Company;

import java.util.ArrayList;
import java.util.List;

public class WebService {

    private final HttpWebClient webClient;
    private final CompanyParser htmlParser;
    private final String baseUrl;

    public WebService(HttpWebClient webClient, CompanyParser htmlParser, String baseUrl) {
        this.webClient = webClient;
        this.htmlParser = htmlParser;
        this.baseUrl = baseUrl;
    }

    public List<Company> fetch(){
        String response = webClient.get(baseUrl);
        List<CompanyDao> companyList = htmlParser.findDetails(response);
        List<Company> companies = new ArrayList<>();
        for(var company: companyList){
            String resp = webClient.get(baseUrl + company.getUrl() + "revenue/");
            company.setEarnings( htmlParser.findEarnings(resp));
            resp = webClient.get(baseUrl + company.getUrl() + "earnings");
            company.setRevenue(htmlParser.findRevenue(resp));
            company.setDescription(htmlParser.findDescription(resp));
            company.setImageUrl(baseUrl+htmlParser.findImageUrl(resp));
            companies.add(company.toCompany());

        }
        return companies;
    }
}
