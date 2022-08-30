package com.na.bilabonnement.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static Connection con;

    private DatabaseConnectionManager(){}

    /**
     *  @author Mathias(Eliot996)
     *  heavily borrowed from:
     *  https://github.com/nicklasdean/spring-jdbc/blob/master/src/main/java/com/example/demo/utility/DatabaseConnectionManager.java
     */
    public static Connection getConnection(){
        if(con != null){
            return con;
        }

        String URL = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // tables er oprettet i DB
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, user, password);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        /*try {
            con.close();
            System.out.println(con == null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
