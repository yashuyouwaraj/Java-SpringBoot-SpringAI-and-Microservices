package com.yashu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletApp")
public class ServletApp extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    // public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                System.out.println("Control in servlet/controller");

                String name =request.getParameter("name");
                String city = request.getParameter("city");

                if(name.equals("Rohan") && city.equals("Pune")){
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<h1>Welcome "+name+" from "+city+"</h1>");
                    System.out.println("Welcome "+name+" from "+city);
                }
                else{
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<h1>Invalid User</h1>");
                    System.out.println("Invalid User");
                }
    }
}
