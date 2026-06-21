package com.tap;

import java.sql.Connection;

import com.tap.util.DBConnection;

public class test {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if(con != null) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED");
        }
    }
}