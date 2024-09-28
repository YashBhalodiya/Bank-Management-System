package com.yashbhalodiya.bank.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static final Dotenv dotenv = Dotenv.load();

    private static final String URL = "jdbc:mysql://localhost:3306/bank_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = dotenv.get("DataBasePassword");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
