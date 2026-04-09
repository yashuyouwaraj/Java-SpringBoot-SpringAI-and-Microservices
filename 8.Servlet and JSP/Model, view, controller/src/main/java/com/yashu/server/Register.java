package com.yashu.server;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.yashu.server.Model;

@WebServlet("/Register")
public class Register extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");
        int age = Integer.parseInt(request.getParameter("age"));

        Model model = new Model();
        model.setName(name);
        model.setEmail(email);
        model.setPassword(password);
        model.setGender(gender);
        model.setCity(city);
        model.setAge(age);

        

        try {
            int row = model.register();
            System.out.println("Registration attempt: Name=" + name + ", Rows affected=" + row);

            HttpSession session = request.getSession();
            session.setAttribute("name", name);

            if (row > 0) {
                response.sendRedirect("/demo/success.jsp");
            } else {
                System.out.println("Insert returned 0 rows for name: " + name);
                response.sendRedirect("/demo/failure.jsp");
            }

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("/demo/failure.jsp");
        }

    }
}
