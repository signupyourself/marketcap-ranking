package com.collector;

import com.collector.httpclients.HttpWebClient;
import com.collector.mocks.MockHttpWebClient;
import com.collector.models.CompanyDao;
import com.collector.parsers.CompanyParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static CompanyParser companyParser;
    private static HttpWebClient httpWebClient;
    private static String homeUrl;
    private static String earningsUrl;
    private static String revenueUrl;

    @BeforeAll
    public static void setUp() {
        companyParser = new CompanyParser();
        httpWebClient = new MockHttpWebClient();
        homeUrl = "https://companiesmarketcap.com/";
        earningsUrl = "https://companiesmarketcap.com/nvidia/earnings/";
        revenueUrl = "https://companiesmarketcap.com/nvidia/revenue/";
    }

    @Test
    public void testFindDescriptionReturnsListOfCompanyDao(){
        List<CompanyDao> companyDaoList =  companyParser.findDetails(httpWebClient.get(homeUrl));
        assertEquals (2, companyDaoList.size());
    }

    @Test
    public void testFindDescriptionReturnsCorrectName(){
        List<CompanyDao> companyDaoList =  companyParser.findDetails(httpWebClient.get(homeUrl));
        assertEquals("NVIDIA", companyDaoList.getFirst().getName());
    }

    @Test
    public void testFindDescriptionReturnsCorrectCountry(){
        List<CompanyDao> companyDaoList =  companyParser.findDetails(httpWebClient.get(homeUrl));
        assertEquals ("USA", companyDaoList.getFirst().getCountry());
    }

    @Test
    public void testFindDescriptionReturnsCorrectMarketCap(){
        List<CompanyDao> companyDaoList =  companyParser.findDetails(httpWebClient.get(homeUrl));
        assertEquals (4.53998542848E12, companyDaoList.getFirst().getMktCap());
    }
    @Test
    public void testFindDescriptionReturnsCorrectUrlString(){
        List<CompanyDao> companyDaoList =  companyParser.findDetails(httpWebClient.get(homeUrl));
        assertEquals ("/nvidia/", companyDaoList.getFirst().getUrl());
    }

    @Test
    public void testFindEarningsReturnsCorrectEarnings(){
        String resp = httpWebClient.get(earningsUrl);
        System.out.println(resp);
        Double earnings = companyParser.findEarnings(resp);
	System.out.println(earnings);
        assertEquals(1.8714E11, earnings);
    }

    @Test
    public void testGetRevenueReturnsCorrectRevenue(){
        String resp = httpWebClient.get(revenueUrl);
        Double revenue = companyParser.findRevenue(resp);
        assertEquals(1.8714E11, revenue);
    }

    @Test
    public void testFindImageUrlReturnsImageUrlString(){
        String resp = httpWebClient.get(earningsUrl);
        String imageUrl = companyParser.findImageUrl(resp);
        assertEquals("/img/company-logos/256/NVDA.webp", imageUrl);
    }

    @Test
    public void testFindDescriptionReturnsCorrectDescription(){

        String resp = httpWebClient.get(earningsUrl);
        String output = companyParser.findDescription(resp);
        String expected = "Nvidia Corporation is one of the largest developers of graphics processors and chipsets for personal computers and game consoles. The head office is in Santa Clara, California.";
        assertEquals(expected,output);
    }

}
