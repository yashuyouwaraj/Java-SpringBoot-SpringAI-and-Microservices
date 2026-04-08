package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                System.out.println("Second Servlet: Service method called");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Response from Second Servlet</h1>");
        writer.close();

        System.out.println("Second Servlet: Service method called again");
    }

}
