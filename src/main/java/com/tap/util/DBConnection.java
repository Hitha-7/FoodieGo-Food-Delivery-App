package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {

            System.out.println("Inside getConnection()");

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver loaded");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_delivery_application",
                    "root",
                    "Hitha@1234");

            System.out.println("Database Connected Successfully");

            return con;

        } catch(Exception e) {

            System.out.println("DB ERROR:");
            e.printStackTrace();
        }

        return null;
    }
}