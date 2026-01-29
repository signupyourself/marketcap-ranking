package com.collector.mocks;

import com.collector.parsers.CompanyParser;

import java.util.ArrayList;
import java.util.List;
import com.collector.models.CompanyDao;


public class MockParser extends CompanyParser{

    @Override
    public List<CompanyDao> findDetails(String html){
        List<CompanyDao> companyDaoList = new ArrayList<>();
        for(int i=0; i<3; i++){
            CompanyDao companyDao = new CompanyDao();
            companyDao.setName("Name"+1);
            companyDao.setCountry("Country"+1);
            companyDao.setMktCap(1234);
            companyDao.setUrl("Url"+1);
            companyDaoList.add(companyDao);
        }
        return companyDaoList;

    }

    public Double findRevenue(String html){
        return 1234.0;
    }

    public Double findEarnings(String html){
        return 1234.0;
    }

    public String findDescription(String html){
        return "Company Description";
    }

    public String findImageUrl(String html){
        return "Company Image Url";
    }

}