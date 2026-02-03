package com.db_support.repositories;

import com.db_support.connection.DBConnection;
import com.db_support.models.Country;
import com.utils.StringEscaper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository {

    private final DBConnection dbConnection;

    public CountryRepository() {
        dbConnection = DBConnection.getDbConnection();
    }

    public CountryRepository(DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void insertCountry(Country country){
        String stmt = String.format(
                "INSERT INTO Countries(Country, MarketCap, Earning, Revenue, MarketCapRanking) VALUES('%s', %f, %f, %f, %d)",
                    StringEscaper.escapeQuotes(country.getCountry()),
                    country.getMarketCap(),
                    country.getEarning(),
                    country.getRevenue(),
                    country.getMarketCapRanking()
        );
        System.out.println(stmt);
        try{
            dbConnection.execute(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteCountries(){
        String stmt = "DELETE FROM Countries";
        try {
            dbConnection.execute(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Country> getCountries(){
        String stmt = "SELECT * from Countries;";
        List<Country> countries = new ArrayList<>();
        try{
            ResultSet resultSet = dbConnection.query(stmt);
            while (resultSet.next()){
                countries.add(parseResult(resultSet));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return countries;
    }

    public Country getCountry(long id){
        String stmt = String.format("SELECT * FROM Countries WHERE id=%d;",id);
        Country country=null;
        try{
            ResultSet resultSet = dbConnection.query(stmt);
            while (resultSet.next()){
               country = parseResult(resultSet);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return country;
    }

    public List<Country> searchCountry(String searchTerm){
        String stmt = "SELECT * FROM Countries WHERE Country ILIKE '%"+StringEscaper.escapeQuotes(searchTerm)+"%';";
        List<Country> countries = new ArrayList<>();
        try{
            ResultSet result = dbConnection.query(stmt);
            while (result.next()){
                countries.add(parseResult(result));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return countries;

    }

    private Country parseResult(ResultSet resultSet) throws SQLException {
        var country = new Country();
        country.setId(resultSet.getLong("Id"));
        country.setCountry(resultSet.getString("Country"));
        country.setMarketCap(resultSet.getDouble("MarketCap"));
        country.setEarning(resultSet.getDouble("Earning"));
        country.setRevenue(resultSet.getDouble("Revenue"));
        country.setMarketCapRanking(resultSet.getInt("MarketCapRanking"));
        return country;
    }

}
