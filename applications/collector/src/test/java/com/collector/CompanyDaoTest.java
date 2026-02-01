package com.collector;

import com.collector.models.CompanyDao;
import com.db_support.models.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDaoTest {

    private static CompanyDao companyDao;

    @BeforeAll
    public static void setup(){
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
    public void testCompanyDaoToCompanyReturnsCompanyObjectWithCorrectValues(){
        Company company = companyDao.toCompany();
        assertEquals("nvidia", company.getName());
        assertEquals("usa", company.getCountry());
        assertEquals(4.5, company.getMarketCap()) ;
        assertEquals("http:://www.nvidia.com", company.getImageUrl()) ;
        assertEquals(15, company.getEarning());
        assertEquals(19, company.getRevenue());
        assertEquals(0, company.getMarketCapRanking());
    }


}
