package com.yashu;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servletLife")
public class ServletLife extends HttpServlet {

    static {
        System.out.println("ServletLife class is loaded");
    }

    public ServletLife(){
        System.out.println("ServletLife object is created");
    }

    public void init(ServletConfig config) throws  ServletException{
        System.out.println("ServletLife init() method is called");
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletLife service() method is called");
    }

    public void destroy(){
        System.out.println("ServletLife destroy() method is called");
    }
}