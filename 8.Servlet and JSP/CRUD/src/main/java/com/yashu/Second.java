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
public class Second extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showPage(request, response);
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String name = session != null ? (String) session.getAttribute("name") : null;
        String city = session != null ? (String) session.getAttribute("city") : null;

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Registration Result</title></head><body>");
        out.println("<h1>Registration Result</h1>");
        if (name != null && city != null) {
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>City: " + city + "</p>");
            out.println("<p>Registration completed successfully.</p>");
        } else {
            out.println("<p>Registration completed, but session data was not found.</p>");
        }
        out.println("</body></html>");
        out.close();
    }
}
