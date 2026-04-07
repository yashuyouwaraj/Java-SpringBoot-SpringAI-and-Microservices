package com.yashu.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.yashu.utils.JavaUtil;

public class Demo1 {
    public static void main(String[] args) {
        Connection connect = null;
        PreparedStatement pstmnt = null;
        Scanner scan = null;

        try {
            connect = JavaUtil.getConnection();

            // statement = connect.createStatement();
            String query = "UPDATE studentinfo SET sage=? WHERE id=?";
            pstmnt = connect.prepareStatement(query);

            System.out.println("Please enter the following details to be stored in DB");
            scan = new Scanner(System.in);
            System.out.println("Enter your age");
            Integer age = scan.nextInt();

            System.out.println("Enter your id");
            Integer id = scan.nextInt();

            pstmnt.setInt(1, age);
            pstmnt.setInt(2, id);

            int rowsAffected = pstmnt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Updation failed");
            } else {
                System.out.println("Updation successful");
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
