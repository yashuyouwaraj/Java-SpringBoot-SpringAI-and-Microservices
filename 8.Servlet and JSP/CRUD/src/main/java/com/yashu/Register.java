package com.yashu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String userpassword = request.getParameter("password");
        String city = request.getParameter("city");
        Integer age = Integer.valueOf(request.getParameter("age"));
        String gender = request.getParameter("gender");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/student";
            String user = "root";
            String password = "Root@1234";

            try (Connection connect = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = connect.prepareStatement("insert into users(name,email,password,city,age,gender) values(?,?,?,?,?,?)")) {

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, userpassword);
                ps.setString(4, city);
                ps.setInt(5, age);
                ps.setString(6, gender);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Registration Successful");
                } else {
                    System.out.println("Registration Failed");
                }
            }
        } catch (ClassNotFoundException | java.sql.SQLException ex) {
            throw new ServletException("Database error", ex);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/second");

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("city", city);

        session.setMaxInactiveInterval(10000);

        System.out.println("Data from First Servlet");
        rd.forward(request, response);
    }
}
