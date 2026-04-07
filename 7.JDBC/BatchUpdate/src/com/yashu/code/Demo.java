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
            String query = "UPDATE studentinfo SET sage=? WHERE id=?";
            pstmnt = connect.prepareStatement(query);

            pstmnt.setInt(1, 20);
            pstmnt.setInt(2, 1);
            pstmnt.addBatch();

            pstmnt.setInt(1, 24);
            pstmnt.setInt(2, 2);
            pstmnt.addBatch();

            pstmnt.setInt(1, 23);
            pstmnt.setInt(2, 3);
            pstmnt.addBatch();

            pstmnt.setInt(1, 25);
            pstmnt.setInt(2, 4);
            pstmnt.addBatch();
            

            pstmnt.executeBatch();

            System.out.println("Check the db table to see the result");

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
