package com.yashu.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

    Connection con = null;

    public AlienRepository() {
        String url = "jdbc:mysql://localhost:3306/restdb";
        String username = "root";
        String password = "Root@1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<Alien>();
        String sql = "SELECT * FROM alien";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                aliens.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aliens;

    }

    public Alien getAliens(int id) {
        Alien a = new Alien();
        String sql = "SELECT * FROM alien where id=" + id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;

    }

    public void createAlien(Alien a1) {
        String sql = "INSERT INTO alien values(?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, a1.getId());
            pstmt.setString(2, a1.getName());
            pstmt.setInt(3, a1.getPoints());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAlien(Alien a1) {
        String sql = "UPDATE alien SET name=?, points=? WHERE id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, a1.getName());
            pstmt.setInt(2, a1.getPoints());
            pstmt.setInt(3, a1.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAlien(int id) {
        Alien a = new Alien();
        String sql = "DELETE FROM alien where id=" + id;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
