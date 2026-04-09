<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.Date" %>
<%! int age=19; %>
<!DOCTYPE html>
<html>
  <head>
    <title>User Form</title>
  </head>

  <body>
    <h1>JSP Web App to generate Dynamic Content</h1>
    <%
    String name = request.getParameter("name");
    String ucity = request.getParameter("city");
    Date date = new Date();
    out.println("Hello " + name + " from " + ucity);
    %>

    <h1>Todays date => <%= date %></h1>
    <h2>Age: <%= age %></h2>
  </body>
</html>
