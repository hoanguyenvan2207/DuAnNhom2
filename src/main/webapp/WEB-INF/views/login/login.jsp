<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #74b9ff, #0984e3);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
        }
        .login-card {
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .login-card .form-control {
            border-radius: 50px;
        }
        .login-card .btn {
            border-radius: 50px;
        }
        .error-message {
            color: #dc3545;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="login-card">
    <h2 class="text-center text-primary mb-4">Login</h2>

    <form action="/login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="tenDangNhap" class="form-control" id="username" placeholder="Enter your username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="matKhau" class="form-control" id="password" placeholder="Enter your password" required>
        </div>

        <c:if test="${not empty error}">
            <div class="error-message text-center mb-3">${error}</div>
        </c:if>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>

        <p class="text-center mt-3">
            <a href="#" class="text-decoration-none text-primary">Forgot your password?</a>
        </p>
    </form>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
