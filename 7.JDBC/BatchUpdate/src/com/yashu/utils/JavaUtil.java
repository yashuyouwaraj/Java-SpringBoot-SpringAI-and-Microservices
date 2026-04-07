package com.yashu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "Root@1234";
        return DriverManager.getConnection(url, user, password);

    }

    public static void closeConnection(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
