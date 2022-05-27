package com.lms;

import java.sql.*;

public class DBConnection {
    private static Connection con;
    private static final String user = "root";
    private static final String pass = "root";
    private static final String url = "jdbc:mysql://localhost:3306/lms?useSSL=false";

    // private String query;
    DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getter for statement
    public static Connection getConnection() {
        return con;
    }
}
