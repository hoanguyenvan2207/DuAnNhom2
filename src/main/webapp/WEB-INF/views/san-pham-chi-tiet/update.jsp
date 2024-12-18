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
    <title>Cập Nhật Sản Phẩm Chi Tiết</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Thêm Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-4">
    <a href="/san-pham-chi-tiet/hien-thi" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-arrow-left"></i> Trở về
    </a>
    <h2 class="text-center">CẬP NHẬT SẢN PHẨM CHI TIẾT</h2>
    <form action="/san-pham-chi-tiet/update/${sanPhamChiTiet.id}" method="post">
        <div class="form-group">
            <label for="maspct"><i class="fas fa-barcode"></i> Mã Sản Phẩm Chi Tiết:</label>
            <input type="text" class="form-control" id="maspct" name="maspct" value="${sanPhamChiTiet.maspct}" required>
        </div>
        <div class="form-group">
            <label for="id_kich_thuoc"><i class="fas fa-ruler"></i> Kích Thước:</label>
            <select class="form-control" id="id_kich_thuoc" name="kichThuoc.id" required>
                <c:forEach var="kichThuoc" items="${dsKichThuoc}">
                    <option value="${kichThuoc.id}" <c:if test="${kichThuoc.id == sanPhamChiTiet.kichThuoc.id}">selected</c:if>>${kichThuoc.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="id_mau_sac"><i class="fas fa-palette"></i> Màu Sắc:</label>
            <select class="form-control" id="id_mau_sac" name="mauSac.id" required>
                <c:forEach var="mauSac" items="${dsMauSac}">
                    <option value="${mauSac.id}" <c:if test="${mauSac.id == sanPhamChiTiet.mauSac.id}">selected</c:if>>${mauSac.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="id_san_pham"><i class="fas fa-box"></i> ID Sản Phẩm:</label>
            <select class="form-control" id="id_san_pham" name="sanPham.id" required>
                <c:forEach var="sanPham" items="${dsSanPham}">
                    <option value="${sanPham.id}" <c:if test="${sanPham.id == sanPhamChiTiet.sanPham.id}">selected</c:if>>${sanPham.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="so_luong"><i class="fas fa-sort-numeric-up"></i> Số Lượng:</label>
            <input type="number" class="form-control" id="so_luong" name="soLuong" value="${sanPhamChiTiet.soLuong}" required>
        </div>
        <div class="form-group">
            <label for="don_gia"><i class="fas fa-dollar-sign"></i> Đơn Giá:</label>
            <input type="number" class="form-control" id="don_gia" name="donGia" value="${sanPhamChiTiet.donGia}" required>
        </div>
        <div class="form-group">
            <label for="trang_thai"><i class="fas fa-clipboard-check"></i> Trạng Thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option value="true" <c:if test="${sanPhamChiTiet.trangThai}">selected</c:if>>Còn hàng</option>
                <option value="false" <c:if test="${!sanPhamChiTiet.trangThai}">selected</c:if>>Hết hàng</option>
            </select>
        </div>
        <button type="submit" class="btn btn-warning" onclick="return confirm('Cập nhật SPCT này?')">
            <i class="fas fa-save"></i> Cập nhật SPCT
        </button>
    </form>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
