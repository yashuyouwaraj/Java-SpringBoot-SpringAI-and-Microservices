<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration Successful</title>

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
            background: linear-gradient(135deg, #43cea2, #185a9d);
        }

        .success-box {
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            width: 400px;
        }

        .success-icon {
            font-size: 60px;
            color: #28a745;
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
            background: #007BFF;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            transition: 0.3s;
        }

        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>

<body>

<div class="success-box">
    <div class="success-icon">✔</div>

    <h2>Registration Successful!</h2>

    <p>
        Welcome,
        <b>${sessionScope.name}</b> 🎉
    </p>

    <p>Your data has been submitted successfully.</p>

    <a href="index.html" class="btn">Go Back</a>
</div>

</body>
</html>