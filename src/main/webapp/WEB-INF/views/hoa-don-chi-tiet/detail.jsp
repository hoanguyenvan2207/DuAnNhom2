<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Hóa Đơn Chi Tiết</title>
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
        <i class="fas fa-file-invoice"></i> Chi Tiết Hóa Đơn Chi Tiết
    </h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>ID Hóa Đơn</th>
            <th>ID Sản Phẩm Chi Tiết</th>
            <th>Số Lượng</th>
            <th>Đơn Giá</th>
            <th>Trạng Thái</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${hoaDonChiTiet.id}</td>
            <td>${hoaDonChiTiet.hoaDon.id}</td>
            <td>${hoaDonChiTiet.sanPhamChiTiet.id}</td>
            <td>${hoaDonChiTiet.soLuong}</td>
            <td>${hoaDonChiTiet.donGia}</td>
            <td>
                <span class="badge ${hoaDonChiTiet.trangThai ? 'badge-success' : 'badge-danger'}">
                    ${hoaDonChiTiet.trangThai ? "Đã xử lý" : "Chưa xử lý"}
                </span>
            </td>
        </tr>
        </tbody>
    </table>
    <a href="/hoa-don-chi-tiet/hien-thi" class="btn btn-primary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
