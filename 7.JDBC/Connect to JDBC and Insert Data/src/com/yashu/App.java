package com.yashu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws Exception {

        // Load and register the MySQL JDBC driver.
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Establish the connection
        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "Iamdon@1947";
        Connection connect = DriverManager.getConnection(url, user, password);

        //creating statement
        Statement statement = connect.createStatement();

        //execute query
        String sql = "INSERT INTO studentinfo(id,sname,sage,scity) VALUES(2,'Shayam',21,'Delhi')";
        int rowCount = statement.executeUpdate(sql);

        //process the result
        if (rowCount > 0) {
            System.out.println("Data saved successfully");
        } else {
            System.out.println("No changes were made");
        }

        //close the resorces
        statement.close();
        connect.close();
    }
}
