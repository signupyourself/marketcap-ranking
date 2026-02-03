package com.db_support.repositories;

import com.db_support.connection.DBConnection;
import com.db_support.models.Company;
import com.utils.StringEscaper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompanyRepository{

    private final DBConnection dbConnection;

    public CompanyRepository() {

        dbConnection = DBConnection.getDbConnection();

    }

    public CompanyRepository(DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void insertCompany(Company company){
        try{
            String stmt = String.format(
                    "INSERT INTO Companies(Name, Country," +
                            " MarketCap, ImageUrl, Description, " +
                            "Revenue, Earning, MarketCapRanking) " +
                            "VALUES('%s', '%s', %f, '%s', '%s', %f, %f, %d);",
                    StringEscaper.escapeQuotes(StringEscaper.escapeAccents(company.getName())),
                    StringEscaper.escapeQuotes(StringEscaper.escapeAccents(company.getCountry())),
                    company.getMarketCap(),
                    StringEscaper.escapeQuotes(StringEscaper.escapeAccents(company.getImageUrl())),
                    StringEscaper.escapeQuotes(StringEscaper.escapeAccents(company.getDescription())),
                    company.getRevenue(),
                    company.getEarning(),
                    company.getMarketCapRanking()
            );
            dbConnection.execute(stmt);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMarketCapRanking(Company company){
        String template ="UPDATE Companies SET " +
                "MarketCapRanking= %d "+
                "WHERE id = %d ;";
        String stmt = String.format(template,
                company.getMarketCapRanking(),
                company.getId()
        );
        try {
            dbConnection.execute(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Company> getCompanies(){
        String statement = "SELECT * FROM Companies ORDER BY MarketCap DESC";
        List<Company> companies = new ArrayList<>();
        try {
            ResultSet result = dbConnection.query(statement);
            while (result.next()) {
                companies.add(parseResultSet(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    public List<Company> getCompaniesByCountry(String country){
        String stmt = String.format("SELECT * FROM Companies WHERE Country = '%s' ORDER BY MarketCap DESC;",country);
        List<Company> companies = new ArrayList<>();
        try{
            ResultSet resultSet = dbConnection.query(stmt);
            while (resultSet.next()){
                companies.add(parseResultSet(resultSet));
            }
            resultSet.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return companies;
    }

    public Company getCompany(long id){
        String stmt = String.format("SELECT * FROM Companies WHERE id=%d", id);
        Company company=null;
        try{
            ResultSet resultSet = dbConnection.query(stmt);
            while (resultSet.next()){
                company = parseResultSet(resultSet);
            }
            resultSet.close();
        }catch (Exception e){
            throw new RuntimeException();
        }
        return company;
    }

    public List<Company> searchCompany(String searchTerm){
        String stmt = "SELECT * FROM Companies WHERE NAME ILIKE '%"+StringEscaper.escapeQuotes(searchTerm)+"%';";
        List<Company> companies = new ArrayList<>();
        try{
            ResultSet resultSet =  dbConnection.query(stmt);
            while (resultSet.next()){
                companies.add(parseResultSet(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    public void deleteCompanies(){
        String stmt = "DELETE FROM Companies";
        try {
            dbConnection.execute(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Company parseResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("Id"));
        company.setName(resultSet.getString("Name"));
        company.setCountry(resultSet.getString("Country"));
        company.setMarketCap(resultSet.getDouble("MarketCap"));
        company.setImageUrl(resultSet.getString("ImageUrl"));
        company.setDescription(resultSet.getString("Description"));
        company.setRevenue(resultSet.getDouble("Revenue"));
        company.setEarning(resultSet.getDouble("Earning"));
        company.setMarketCapRanking(resultSet.getInt("MarketCapRanking"));
        return company;
    }
}