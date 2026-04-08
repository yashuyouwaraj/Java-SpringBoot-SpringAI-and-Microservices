package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/first")
public class LoginServlet extends HttpServlet {

    @SuppressWarnings("ConvertToTryWithResources")
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String city = request.getParameter("city");

        RequestDispatcher rd = request.getRequestDispatcher("/second");

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("city", city);

        session.setMaxInactiveInterval(10000);

        rd.forward(request, response);

        PrintWriter wr = response.getWriter();
        wr.println("<h1>Data from First Servlet</h1>");
        wr.println("<h2>Name: " + name + "</h2>");
        wr.println("<h2>City: " + city + "</h2>");
        wr.close();

        System.out.println("Data from First Servlet");


    }
}
