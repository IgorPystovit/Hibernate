package com.epam.igorpystovit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;
    private static final String user = "wage";
    private static final String pass = "root";
    private static final String url = "jdbc:mysql://localhost:3306/Airport?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public static Connection getConnection(){
        if (connection == null) {
            synchronized (ConnectionManager.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection
                                (url, user, pass);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

}
