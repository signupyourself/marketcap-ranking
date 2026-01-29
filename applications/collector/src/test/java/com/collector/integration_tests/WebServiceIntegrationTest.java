package com.collector.integration_tests;

import com.collector.httpclients.HttpWebClient;
import com.collector.parsers.CompanyParser;
import com.collector.services.WebService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebServiceIntegrationTest {

    @Test
    public void testWebServiceFetchesData(){
        WebService webService = new WebService(
                new HttpWebClient(),
                new CompanyParser(),
                "https://companiesmarketcap.com/"
        );
        var companies = webService.fetch();
        assertEquals(100, companies.size());
    }
}
