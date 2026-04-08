<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
        }

        .form-box {
            width: 350px;
            margin: 80px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .gender {
            margin-top: 10px;
        }

        .gender input {
            margin-right: 5px;
        }

        button {
            width: 100%;
            margin-top: 20px;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>

<div class="form-box">
    <h2>User Form</h2>

    <form action="secondApp" method="post">

        <label>Name</label>
        <input type="text" name="name" required>

        <label>City</label>
        <input type="text" name="city" required>

        <label>Age</label>
        <input type="number" name="age" required>

        <label>Gender</label>
        <div class="gender">
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
        </div>

        <button type="submit">Submit</button>

    </form>
</div>

</body>
</html>