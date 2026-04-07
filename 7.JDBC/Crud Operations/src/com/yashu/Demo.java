package com.yashu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yashu.util.JavaUtil;

public class Demo {

    public static void main(String[] args) {
        Connection connect = null;
        PreparedStatement updateStatement = null;
        PreparedStatement checkStatement = null;
        ResultSet resultSet = null;

        try {
            connect = JavaUtil.getConnection();

            int studentId = 2;
            int newAge = 18;

            String updateSql = "UPDATE studentinfo SET sage = ? WHERE id = ?";
            updateStatement = connect.prepareStatement(updateSql);
            updateStatement.setInt(1, newAge);
            updateStatement.setInt(2, studentId);

            int rowsCount = updateStatement.executeUpdate();
            if (rowsCount > 0) {
                System.out.println("Update successful!");
                return;
            }

            String checkSql = "SELECT sage FROM studentinfo WHERE id = ?";
            checkStatement = connect.prepareStatement(checkSql);
            checkStatement.setInt(1, studentId);
            resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                int currentAge = resultSet.getInt("sage");
                if (currentAge == newAge) {
                    System.out.println("No update needed. The age is already " + newAge + ".");
                } else {
                    System.out.println("Update failed even though the record exists.");
                }
            } else {
                System.out.println("Update failed. No student found with id = " + studentId + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavaUtil.closeConnection(resultSet, checkStatement, updateStatement, connect);
        }
    }

}
