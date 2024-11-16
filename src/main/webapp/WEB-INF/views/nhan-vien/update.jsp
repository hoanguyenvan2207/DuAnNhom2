<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Nhân Viên</title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <a href="/nhan-vien/hien-thi" class="btn btn-secondary btn-sm mb-4">
        <i class="fas fa-arrow-left"></i> Trở Về
    </a>
    <h2 class="text-center">CẬP NHẬT NHÂN VIÊN</h2>

    <form action="/nhan-vien/update/${nhanVien.id}" method="post">
        <div class="form-group">
            <label for="ma_nv">Mã NV:</label>
            <input type="text" class="form-control" id="ma_nv" name="maNv" value="${nhanVien.maNv}" required>
        </div>
        <div class="form-group">
            <label for="ten">Tên:</label>
            <input type="text" class="form-control" id="ten" name="ten" value="${nhanVien.ten}" required>
        </div>
        <div class="form-group">
            <label for="ten_dang_nhap">Tên Đăng Nhập:</label>
            <input type="text" class="form-control" id="ten_dang_nhap" name="tenDangNhap" value="${nhanVien.tenDangNhap}" required>
        </div>
        <div class="form-group">
            <label for="mat_khau">Mật Khẩu:</label>
            <input type="password" class="form-control" id="mat_khau" name="matKhau" value="${nhanVien.matKhau}" required>
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng Thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option value="true" ${nhanVien.trangThai ? 'selected' : ''}>Đang làm việc</option>
                <option value="false" ${!nhanVien.trangThai ? 'selected' : ''}>Đã nghỉ việc</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary" onclick="return confirm('Cập nhật nhân viên này ?')">
            <i class="fas fa-user-edit"></i> Cập Nhật Nhân Viên
        </button>
    </form>
</div>

<!-- Nhúng Bootstrap JS, jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
