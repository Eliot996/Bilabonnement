package com.na.bilabonnement.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }

        //Properties file
        try(InputStream propertiesFile = new FileInputStream("src/main/resources/application.properties")){
            Properties props = new Properties();
            props.load(propertiesFile);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
            conn = DriverManager.getConnection(url, username, password);
        }

        //Environment Variables
        /*try {
            url = System.getenv("db.url");
            username = System.getenv("db.username");
            password = System.getenv("db.password");
            conn = DriverManager.getConnection(url, username, password);
        }*/

        catch(SQLException | IOException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conn = null;
    }
}
