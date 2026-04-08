<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>

<h2>User Registration</h2>

<form action="register" method="post">

    Name: <input type="text" name="name" required><br><br>

    Email: <input type="email" name="email" required><br><br>

    Password: <input type="password" name="password" required><br><br>

    Gender:
    <input type="radio" name="gender" value="Male"> Male
    <input type="radio" name="gender" value="Female"> Female
    <br><br>

    Skills:
    <input type="checkbox" name="skills" value="Java"> Java
    <input type="checkbox" name="skills" value="Python"> Python
    <input type="checkbox" name="skills" value="JS"> JavaScript
    <br><br>

    <button type="submit">Submit</button>

</form>

</body>
</html>