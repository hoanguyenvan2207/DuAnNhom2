<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Hóa Đơn</title>
    <!-- Thêm link Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            margin-top: 20px;
        }
        h2 {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>
        <i class="fas fa-file-invoice"></i> Chi Tiết Hóa Đơn
    </h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Title</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>ID</td>
            <td>${hoaDon.id}</td>
        </tr>
        <tr>
            <td>Nhân Viên</td>
            <td>${hoaDon.nhanVien.ten}</td>
        </tr>
        <tr>
            <td>Khách Hàng</td>
            <td>${hoaDon.khachHang.ten}</td>
        </tr>
        <tr>
            <td>Ngày mua</td>
            <td>${hoaDon.ngayMuaHang}</td>
        </tr>
        <tr>
            <td>Trạng thái</td>
            <td>${hoaDon.trangThai ? "Đã thanh toán" : "Chưa thanh toán"}</td>
        </tr>
        </tbody>
    </table>
    <a href="/hoa-don/hien-thi" class="btn btn-primary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
</div>

<!-- Thêm link Bootstrap JS và jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
