//package com.db_support.repositories;
//
//import com.db_support.connection.DBConnection;
//import com.db_support.models.Company;
//import com.db_support.models.CompanyDao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CompanyDaoRepository {
//
//    DBConnection dbConnection;
//
//    CompanyDaoRepository(){
//        dbConnection = DBConnection.getDbConnection();
//    }
//
//    public List<CompanyDao> findAll(){
//        String stmt = "SELECT * FROM CompanyDao;";
//        List<CompanyDao> companyDaoList = new ArrayList<>();
//        try{
//            ResultSet resultSet = dbConnection.query(stmt);
//            while (resultSet.next()){
//                companyDaoList.add(parseResultSet(resultSet));
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return companyDaoList;
//    }
//
//    public void  insertCompanyDao(){
//        String.format("INSERT INTO CompanyDao() VALUES(%d, %s, %s, )")
//        try{
//            ResultSet resultSet = dbConnection.query(stmt);
//            while (resultSet.next()){
//                companyDaoList.add(parseResultSet(resultSet));
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return companyDaoList;
//    }
//
//    private CompanyDao parseResultSet(ResultSet resultSet) throws SQLException {
//        CompanyDao companyDao = new CompanyDao();
//        companyDao.setId(resultSet.getLong("Id"));
//        companyDao.setName(resultSet.getString("Name"));
//        companyDao.setCountry(resultSet.getString("Country"));
//        companyDao.setMarketCap(resultSet.getDouble("MarketCap"));
//        companyDao.setImageUrl(resultSet.getString("ImageUrl"));
//        companyDao.setDescription(resultSet.getString("Description"));
//        companyDao.setRevenue(resultSet.getDouble("Revenue"));
//        companyDao.setEarning(resultSet.getDouble("Earning"));
//        return companyDao;
//    }
//}
