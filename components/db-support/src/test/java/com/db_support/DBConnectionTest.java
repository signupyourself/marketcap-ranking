package com.db_support;

import com.db_support.connection.DBConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DBConnectionTest {

    @Test
    public void testDBConnection(){
        DBConnection dbConnection = DBConnection.getDbConnection ("jdbc:postgresql://localhost:5433/Test", "admin", "1234");
        assertNotEquals(null, dbConnection);
    }
}
