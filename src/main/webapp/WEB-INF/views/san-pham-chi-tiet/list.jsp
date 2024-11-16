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
    <title>Danh Sách Sản Phẩm Chi Tiết</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Thêm Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="mt-4 container">
    <a href="/home/index" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-home"></i> Home
    </a>
    <h2 class="text-center">DANH SÁCH SẢN PHẨM CHI TIẾT</h2>
    <form action="/san-pham-chi-tiet/search" method="get">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="keyword" placeholder="Tìm kiếm ..." value="${keyword}">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
        </div>
    </form>
    <table class="table table-bordered table-hover table-striped">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Mã SPCT</th>
            <th>Kích Thước</th>
            <th>Màu Sắc</th>
            <th>Tên Sản Phẩm</th>
            <th>Số Lượng</th>
            <th>Đơn Giá</th>
            <th>Trạng Thái</th>
            <th>Hoạt động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sanPhamChiTiet" items="${dsSanPhamChiTiet}">
            <tr>
                <td>${sanPhamChiTiet.id}</td>
                <td>${sanPhamChiTiet.maspct}</td>
                <td>${sanPhamChiTiet.kichThuoc.ten}</td>
                <td>${sanPhamChiTiet.mauSac.ten}</td>
                <td>${sanPhamChiTiet.sanPham.ten}</td>
                <td>${sanPhamChiTiet.soLuong}</td>
                <td>${sanPhamChiTiet.donGia}</td>
                <td>
                    <span class="badge ${sanPhamChiTiet.trangThai ? 'badge-success' : 'badge-danger'}">
                            ${sanPhamChiTiet.trangThai ? 'Còn hàng' : 'Hết hàng'}
                    </span>
                </td>
                <td>
                    <a href="/san-pham-chi-tiet/update/${sanPhamChiTiet.id}" class="btn btn-warning btn-sm">
                        <i class="fas fa-edit"></i> Cập nhật
                    </a>
                    <a href="/san-pham-chi-tiet/delete/${sanPhamChiTiet.id}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">
                        <i class="fas fa-trash-alt"></i> Xóa
                    </a>
                    <a href="/san-pham-chi-tiet/detail/${sanPhamChiTiet.id}" class="btn btn-info btn-sm">
                        <i class="fas fa-info-circle"></i> Chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/san-pham-chi-tiet/add" class="btn btn-success">
        <i class="fas fa-plus-circle"></i> Thêm mới SPCT
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
