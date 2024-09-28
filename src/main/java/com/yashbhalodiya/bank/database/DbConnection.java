package com.yashbhalodiya.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bank_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Y@shBh@lodiy@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
