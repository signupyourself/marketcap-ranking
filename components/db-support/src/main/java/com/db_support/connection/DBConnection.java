package com.db_support.connection;

import java.sql.*;

public class DBConnection{

    private final Connection connection;

    private volatile static DBConnection dbConnection = null;

    //returns a singleton
    public static DBConnection getDbConnection(){
        if(dbConnection == null){
            synchronized (DBConnection.class){
                if(dbConnection == null){
                    dbConnection = new DBConnection();
                }
            }
        }
        return dbConnection;
    }

    public static DBConnection getDbConnection(String jdbcURL, String userName, String password){
        if(dbConnection == null){
            synchronized (DBConnection.class){
                if(dbConnection == null){
                    dbConnection = new DBConnection(jdbcURL,userName,password);
                }
            }
        }
        return dbConnection;
    }


    private DBConnection(){

        String jdbcURL =  System.getenv("JDBC_DATABASE_URL");
        String userName = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        String connectionUrl = String.format("%s?user=%s&password=%s",jdbcURL, userName, password);

        try {
            Class.forName("org.postgresql.Driver");
            connection
                    = DriverManager.getConnection(
                     connectionUrl);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //constructor used for testing
    private DBConnection(String jdbcURL, String userName, String password){
        try {
            Class.forName("org.postgresql.Driver");
            String connectionUrl = String.format("%s?user=%s&password=%s",jdbcURL, userName, password);
             connection
                     = DriverManager.getConnection(
                     connectionUrl);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(String sql) throws SQLException {
        Statement cur = connection.createStatement();
        cur.execute(sql);
    }

    public ResultSet query(String sql) throws SQLException {
        Statement cur = connection.createStatement();
        return cur.executeQuery(sql);
    }
}
