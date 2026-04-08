package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    @SuppressWarnings("ConvertToTryWithResources")
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Data from Second Servlet");

        HttpSession session = request.getSession(false);
        
        String name = (String) session.getAttribute("name");
        String city = (String) session.getAttribute("city");

        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Second App</title></head>");
        writer.println("<body>");
        writer.println("<h1>Data from Second Servlet</h1>");
        writer.println("<h2>Name: " + name + "</h2>");
        writer.println("<h2>City: " + city + "</h2>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();


    }
}
