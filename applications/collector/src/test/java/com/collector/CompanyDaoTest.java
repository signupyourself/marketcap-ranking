package com.collector;

import com.collector.models.CompanyDao;
import com.db_support.models.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDaoTest {

    private static CompanyDao companyDao;

    @BeforeAll
    public static void setCompanyDao(){
        companyDao = new CompanyDao();
        companyDao.setName("nvidia");
        companyDao.setCountry("usa");
        companyDao.setImageUrl("http:://www.nvidia.com");
        companyDao.setDescription("nvidia is a semiconductor company");
        companyDao.setMktCap(4.5);
        companyDao.setEarnings(15);
        companyDao.setRevenue(19);
    }


    @Test
    public void testCompanyDaoToCompanyCreatesCompanyObjectWithCorrectValues(){
        Company company = companyDao.toCompany();
        assertEquals(company.getName(), "nvidia");
        assertEquals(company.getCountry(), "usa");
        assertEquals(company.getMarketCap(), 4.5) ;
        assertEquals(company.getImageUrl(), "http:://www.nvidia.com") ;
        assertEquals(company.getEarning(), 15);
        assertEquals(company.getRevenue(), 19);
        assertEquals(company.getMarketCapRanking(), 0);
    }


}
