<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
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
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Sản Phẩm Chi Tiết</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center">CHI TIẾT SẢN PHẨM CHI TIẾT</h2>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <td>${sanPhamChiTiet.id}</td>
        </tr>
        <tr>
            <th>Mã Sản Phẩm Chi Tiết</th>
            <td>${sanPhamChiTiet.maspct}</td>
        </tr>
        <tr>
            <th>Kích Thước</th>
            <td>${sanPhamChiTiet.kichThuoc.ten}</td>
        </tr>
        <tr>
            <th>Màu Sắc</th>
            <td>${sanPhamChiTiet.mauSac.ten}</td>
        </tr>
        <tr>
            <th>Tên Sản Phẩm</th>
            <td>${sanPhamChiTiet.sanPham.ten}</td>
        </tr>
        <tr>
            <th>Số Lượng</th>
            <td>${sanPhamChiTiet.soLuong}</td>
        </tr>
        <tr>
            <th>Đơn Giá</th>
            <td>${sanPhamChiTiet.donGia}</td>
        </tr>
        <tr>
            <th>Trạng Thái</th>
            <td><span class="badge ${sanPhamChiTiet.trangThai ? 'badge-success' : 'badge-danger'}">
                ${sanPhamChiTiet.trangThai ? 'Còn hàng' : 'Hết hàng'}</span>
            </td>
        </tr>
    </table>
    <a href="/san-pham-chi-tiet/hien-thi" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
