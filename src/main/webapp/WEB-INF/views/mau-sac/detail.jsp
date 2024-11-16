<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Màu Sắc</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
    </style>
</head>
<body>

<div class="container mt-4">
    <h2 class="text-center">
        <i class="fas fa-paint-brush"></i> CHI TIẾT MÀU SẮC
    </h2>

    <table class="table table-bordered">
        <tr>
            <th>Mã</th>
            <td>${mauSac.ma}</td>
        </tr>
        <tr>
            <th>Tên</th>
            <td>${mauSac.ten}</td>
        </tr>
        <tr>
            <th>Trạng Thái</th>
            <td>${mauSac.trangThai ? 'Hoạt động' : 'Không hoạt động'}</td>
        </tr>
    </table>

    <div class="text-center">
        <a href="/mau-sac/hien-thi" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Quay lại
        </a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
