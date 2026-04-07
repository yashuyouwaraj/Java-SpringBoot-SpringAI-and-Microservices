package com.yashu.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.yashu.utils.JavaUtil;

public class Demo2 {
    public static void main(String[] args) {
        Connection connect = null;
        PreparedStatement pstmnt = null;
        Scanner scan = null;

        try {
            connect = JavaUtil.getConnection();

            // statement = connect.createStatement();
            String query = "DELETE FROM studentinfo WHERE id=?";
            pstmnt = connect.prepareStatement(query);

            System.out.println("Please enter the following details to be delete from DB");
            scan = new Scanner(System.in);

            System.out.println("Enter your id");
            Integer id = scan.nextInt();

            pstmnt.setInt(1, id);

            int rowsAffected = pstmnt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Deletion failed");
            } else {
                System.out.println("Deletion successful");
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
