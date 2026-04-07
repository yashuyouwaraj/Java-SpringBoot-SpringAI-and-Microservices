package com.yashu.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.yashu.utils.JavaUtil;

public class Demo3 {
    public static void main(String[] args) {
        Connection connect = null;
        PreparedStatement pstmnt = null;
        Scanner scan = null;
        ResultSet rs=null;

        try {
            connect = JavaUtil.getConnection();

            // statement = connect.createStatement();
            String query = "SELECT id,sname,sage,scity FROM studentinfo WHERE id=?";
            pstmnt = connect.prepareStatement(query);

            System.out.println("Please enter the following details to be retrived from DB");
            scan = new Scanner(System.in);

            System.out.println("Enter your id");
            Integer id = scan.nextInt();

            pstmnt.setInt(1, id);

            rs= pstmnt.executeQuery();
            if (rs.next()) {
                Integer sid=rs.getInt(1);
                String sname=rs.getString(2);
                Integer sage=rs.getInt(3);
                String scity=rs.getString(4);

                System.out.println(sid+" "+sname+" "+sage+" "+scity);
            } else {
                System.out.println("No record found");
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
