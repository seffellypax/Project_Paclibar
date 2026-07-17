package com.example.project_paclibar.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ewallet_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "";
    public static Connection getConnection() {
        try {
            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );
            System.out.println("Database Connected!");
            return connection;
        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}