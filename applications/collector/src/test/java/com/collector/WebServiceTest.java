package com.collector;

import com.collector.mock.HttpWebClientMock;
import com.collector.mock.MockParser;
import com.collector.services.WebService;
import com.db_support.models.Company;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebServiceTest {

    @Test
    public void testFetchReturnsCorrectNumberOfCompanies() {
        WebService webService = new WebService(new HttpWebClientMock(), new MockParser(), "");
        List<Company> companies =  webService.fetch();
        assertEquals (3, companies.size());
    }
}
