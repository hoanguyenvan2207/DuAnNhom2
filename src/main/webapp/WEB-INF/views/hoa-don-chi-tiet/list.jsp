<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Hóa Đơn Chi Tiết</title>
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
    <a href="/home/index" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-home"></i> Home
    </a>
    <h2 class="text-center">
        <i class="fas fa-file-invoice"></i> DANH SÁCH HÓA ĐƠN CHI TIẾT
    </h2>
    <form action="/hoa-don-chi-tiet/hien-thi" method="get">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="ma" placeholder="Tìm kiếm...">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
        </div>
    </form>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>ID Hóa Đơn</th>
            <th>ID Sản Phẩm Chi Tiết</th>
            <th>Số Lượng</th>
            <th>Đơn Giá</th>
            <th>Trạng Thái</th>
            <th>Hoạt Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="stt" var="hoaDonChiTiet" items="${dsHoaDonChiTiet}">
            <tr>
                <td>${stt.count}</td>
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
                <td>
                    <a href="/hoa-don-chi-tiet/detail?id=${hoaDonChiTiet.id}" class="btn btn-info">
                        <i class="fas fa-info-circle"></i> Chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
