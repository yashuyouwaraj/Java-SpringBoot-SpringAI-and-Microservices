<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', sans-serif;
        }

        body {
            height: 100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            width: 400px;
            padding: 30px;
            border-radius: 15px;
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            color: #fff;
        }

        .container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .input-group {
            margin-bottom: 15px;
        }

        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
        }

        .input-group input,
        .input-group select {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 8px;
            outline: none;
        }

        .gender-group {
            display: flex;
            gap: 15px;
        }

        .gender-group label {
            font-size: 14px;
        }

        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #ff7eb3;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn:hover {
            background: #ff4e8a;
        }

        .footer {
            text-align: center;
            margin-top: 10px;
            font-size: 14px;
        }

        .footer a {
            color: #ffd;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="container">
    <h2>Register</h2>

    <form action="RegisterServlet" method="post">

        <div class="input-group">
            <label>Full Name</label>
            <input type="text" name="name" required>
        </div>

        <div class="input-group">
            <label>Email</label>
            <input type="email" name="email" required>
        </div>

        <div class="input-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>

        <div class="input-group">
            <label>City</label>
            <input type="text" name="city">
        </div>

        <div class="input-group">
            <label>Age</label>
            <input type="number" name="age" min="1">
        </div>

        <div class="input-group">
            <label>Gender</label>
            <div class="gender-group">
                <label><input type="radio" name="gender" value="Male"> Male</label>
                <label><input type="radio" name="gender" value="Female"> Female</label>
            </div>
        </div>

        <button type="submit" class="btn">Register</button>

        <div class="footer">
            Already have an account? <a href="#">Login</a>
        </div>

    </form>
</div>

</body>
</html>