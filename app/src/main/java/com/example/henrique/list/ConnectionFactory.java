package com.example.henrique.list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("jdbc:mysql:192.169.198.138");
            return DriverManager.getConnection("com.mysql.jdbc.Driver","root","4MotiLive");

        }catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}


