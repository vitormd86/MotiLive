package com.example.henrique.list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public  class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://192.169.198.138:3306/motilive","root","4linux");

        }catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}


