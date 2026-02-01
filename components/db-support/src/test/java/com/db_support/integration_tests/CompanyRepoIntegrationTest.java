package com.db_support.integration_tests;

import com.db_support.connection.DBConnection;
import com.db_support.models.Company;
import com.db_support.repositories.CompanyRepository;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CompanyRepoIntegrationTest {

    private static DBConnection dbConnection;
    private static CompanyRepository companyRepository;

    @BeforeEach
    public  void setUp(){
        dbConnection = DBConnection.getDbConnection("jdbc:postgresql://localhost:5433/Test", "admin", "1234");
        companyRepository = new CompanyRepository(dbConnection);
        Company company = new Company();
        company.setName("NVIDIA");
        company.setCountry("USA");
        company.setImageUrl("http://imageUrl");
        company.setMarketCap(4555.0);
        company.setEarning(50.00);
        company.setDescription("Semiconductor");
        company.setMarketCapRanking(1);
        companyRepository.insertCompany(company);
    }

    @AfterEach
    public  void tearDown(){
        try{
            dbConnection.execute("DELETE FROM Companies");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetCompaniesReturnsListOfCompanies(){
        List<Company> companies = companyRepository.getCompanies();
        assertEquals(1, companyRepository.getCompanies().size());
    }

    @Test
    public void testSetMarketCapRankingUpdatesMarketCapRanking(){
        List<Company> companies = companyRepository.getCompanies();
        companies.getFirst().setMarketCapRanking(4);
        companyRepository.updateMarketCapRanking(companies.getFirst());
        companies = companyRepository.getCompanies();
        assertEquals(4, companies.getFirst().getMarketCapRanking());
    }

    @Test
    public void testGetCompanyWithInvalidIdReturnsNull(){
        Company result = companyRepository.getCompany(4);
        assertNull(result);
    }

    @Test
    public void testGetCompanyWithValidIdReturnsCompanyObject(){
        List<Company> companies = companyRepository.getCompanies();
        Company result = companyRepository.getCompany(companies.getFirst().getId());
        assertEquals("NVIDIA", result.getName());
    }

    @Test
    public void testSearchCompanyWithCompanySavedInDBReturnsNonEmptyList(){
        List<Company> companies = companyRepository.searchCompany("NViDia");
        assertEquals(1, companies.size());
        assertEquals("NVIDIA", companies.getFirst().getName());
    }

    @Test
    public void testSearchCompanyWithCompanyNotSavedInDBReturnsEmptyList(){
        List<Company> companies = companyRepository.searchCompany("Microsoft");
        assertEquals(0, companies.size());
    }


    @Test
    public void testSearchCompanyWithQuotesRemovesQuotesAndReturnsSearchResults() {
        List<Company> companies = companyRepository.searchCompany("Nv'idia");
        assertEquals(0, companies.size());
    }

    @Test
    public void testDeleteCompaniesDeletesAllRows(){
        companyRepository.deleteCompanies();
        List<Company> companies = companyRepository.getCompanies();
        assertEquals(0,companies.size());
    }

}
