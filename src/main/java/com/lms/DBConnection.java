package com.lms;

import java.sql.*;

public class DBConnection {
    private Connection con;
    private static Statement st;
    private static final String user = "root";
    private static final String pass = "root";
    private static final String url = "jdbc:mysql://localhost:3306/lms?useSSL=false";
    private ResultSet rs;

    // private String query;
    DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            // System.out.println(rs);
            // while (rs.next()) {
            // System.out.println(rs.getString(4));
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getter for statement
    public static Statement getStatement() {
        return st;
    }

    // public void connectDB() {
    // try {
    // // Class.forName("com.mysql.jdbc.Driver");
    // con = DriverManager.getConnection(url, user, pass);
    // st = con.createStatement();
    // } catch (SQLException e) {
    // e.getMessage();
    // }
    // // return st;
    // }
}
