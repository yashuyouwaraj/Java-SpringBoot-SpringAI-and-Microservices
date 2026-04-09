<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration Failed</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #ff416c, #ff4b2b);
        }

        .error-box {
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            width: 400px;
        }

        .error-icon {
            font-size: 60px;
            color: #dc3545;
        }

        h2 {
            margin-top: 15px;
            color: #333;
        }

        p {
            margin-top: 10px;
            color: #666;
        }

        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #dc3545;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            transition: 0.3s;
        }

        .btn:hover {
            background: #a71d2a;
        }
    </style>
</head>

<body>

<div class="error-box">
    <div class="error-icon">✖</div>

    <h2>Registration Failed</h2>

    <p>
        Sorry,
        <b><%= request.getAttribute("username") != null ? request.getAttribute("username") : "User" %></b> 😔
    </p>

    <p>
        Something went wrong. Please try again.
    </p>

    <a href="index.html" class="btn">Try Again</a>
</div>

</body>
</html>