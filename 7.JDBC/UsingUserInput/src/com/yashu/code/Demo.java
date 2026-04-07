package com.yashu.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.yashu.utils.JavaUtil;

public class Demo {

    public static void main(String[] args) {
        Connection connect = null;
        PreparedStatement pstmnt = null;
        Scanner scan = null;

        try {
            connect = JavaUtil.getConnection();

            // statement = connect.createStatement();
            String query = "INSERT INTO studentinfo(id,sname,sage,scity) VALUES(?,?,?,?)";
            pstmnt = connect.prepareStatement(query);

            System.out.println("Please enter the following details to be stored in DB");
            scan = new Scanner(System.in);
            System.out.println("Enter your id");
            Integer id = scan.nextInt();

            System.out.println("Enter your name");
            String name = scan.next();

            System.out.println("Enter your age");
            Integer age = scan.nextInt();

            System.out.println("Enter your city");
            String city = scan.next();

            pstmnt.setInt(1, id);
            pstmnt.setString(2, name);
            pstmnt.setInt(3, age);
            pstmnt.setString(4, city);

            int rowsAffected = pstmnt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Insertion failed");
            } else {
                System.out.println("Insertion successful");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JavaUtil.closeConnection(pstmnt, connect);
            if (scan != null) {
                scan.close();
            }
        }
    }
}
