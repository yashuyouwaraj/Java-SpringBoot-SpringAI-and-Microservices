package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/secondApp")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String city = request.getParameter("city");

        PrintWriter writer = response.getWriter();

            writer.println("<html><head><title>Second App</title></head>");
            writer.println("<body>");
            writer.println("<h1>Welcome, " + name + " from " + city + "!</h1>");
            writer.println("</body></html>");

            

       writer.close();
    }
}