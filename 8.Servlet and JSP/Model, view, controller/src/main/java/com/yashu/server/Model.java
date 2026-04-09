package com.yashu.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Model {
    private String name;
    private String email;
    private String password;
    private String gender;
    private String city;
    private int age;
    private Connection connect;
    private PreparedStatement pstmnt;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int register() throws SQLException{
        connect = JavaUtil.getConnection();
        String sql = "INSERT INTO personalinfo (name,email,password,gender,age,city) VALUES (?,?,?,?,?,?)";
        pstmnt = connect.prepareStatement(sql);
        pstmnt.setString(1, name);
        pstmnt.setString(2, email);
        pstmnt.setString(3, password);
        pstmnt.setString(4, gender);
        pstmnt.setInt(5, age);
        pstmnt.setString(6, city);
        int rowsAffected = pstmnt.executeUpdate();

        JavaUtil.closeConnection(connect, pstmnt);

        return rowsAffected;
    }

}
