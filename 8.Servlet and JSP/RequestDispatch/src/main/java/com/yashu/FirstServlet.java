package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("First Servlet: Service method called");

        RequestDispatcher reqDisp =  request.getRequestDispatcher("/second");

        // reqDisp.forward(request, response);
        reqDisp.include(request, response);

        PrintWriter writer = response.getWriter();
        writer.println("<h1>Response from First Servlet</h1>");

        writer.close();

        System.out.println("First Servlet: Service method called again");
    }
}
