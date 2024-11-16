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
    <title>Thêm Sản Phẩm Chi Tiết</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <a href="/san-pham-chi-tiet/hien-thi" class="btn btn-secondary btn-sm">
        <i class="fas fa-arrow-left"></i> Trở về
    </a>
    <h2 class="text-center">THÊM SẢN PHẨM CHI TIẾT</h2>
    <form action="/san-pham-chi-tiet/create" method="post">
        <div class="form-group">
            <label for="maspct">Mã Sản Phẩm Chi Tiết:</label>
            <input type="text" class="form-control" id="maspct" name="maspct" required>
        </div>
        <div class="form-group">
            <label for="id_kich_thuoc">Kích Thước:</label>
            <select class="form-control" id="id_kich_thuoc" name="kichThuoc.id" required>
                <option selected disabled hidden="hidden" value="">---Chọn---</option>
                <c:forEach var="kichThuoc" items="${dsKichThuoc}">
                    <option value="${kichThuoc.id}">${kichThuoc.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="id_mau_sac">Màu Sắc:</label>
            <select class="form-control" id="id_mau_sac" name="mauSac.id" required>
                <option selected disabled hidden="hidden" value="">---Chọn---</option>
                <c:forEach var="mauSac" items="${dsMauSac}">
                    <option value="${mauSac.id}">${mauSac.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="id_san_pham">Sản Phẩm:</label>
            <select class="form-control" id="id_san_pham" name="sanPham.id" required>
                <option selected disabled hidden="hidden" value="">---Chọn---</option>
                <c:forEach var="sanPham" items="${dsSanPham}">
                    <option value="${sanPham.id}">${sanPham.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="so_luong">Số Lượng:</label>
            <input type="number" class="form-control" id="so_luong" name="soLuong" required>
        </div>
        <div class="form-group">
            <label for="don_gia">Đơn Giá:</label>
            <input type="number" class="form-control" id="don_gia" name="donGia" required>
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng Thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option selected disabled hidden="hidden" value="">---Chọn---</option>
                <option value="true">Còn hàng</option>
                <option value="false">Hết hàng</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success" onclick="return confirm('Thêm mới chi tiết sản phẩm này?')">
            <i class="fas fa-plus-circle"></i> Thêm mới SPCT
        </button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
