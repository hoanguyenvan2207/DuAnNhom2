<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách nhân viên</title>

    <!-- Nhúng Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Nhúng Font Awesome cho biểu tượng -->
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
<div class="container">
    <a href="/home/index" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-home"></i> Home
    </a>

    <h2 class="mt-4 mb-3 text-center">DANH SÁCH NHÂN VIÊN</h2>
    <form action="/nhan-vien/search" method="get">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="ma" placeholder="Tìm kiếm...">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
        </div>
    </form>

    <!-- Bảng danh sách nhân viên -->
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Mã NV</th>
            <th>Tên</th>
            <th>Tên đăng nhập</th>
            <th>Mật khẩu</th>
            <th>Trạng thái</th>
            <th>Hoạt động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="stt" var="nhanVien" items="${dsNhanVien}">
            <tr>
                <td>${stt.count}</td>
                <td>${nhanVien.id}</td>
                <td>${nhanVien.maNv}</td>
                <td>${nhanVien.ten}</td>
                <td>${nhanVien.tenDangNhap}</td>
                <td>${nhanVien.matKhau}</td>
                <td>
                    <span class="badge ${nhanVien.trangThai ? 'badge-success' : 'badge-danger'}">
                            ${nhanVien.trangThai ? "Đang làm việc" : "Đã nghỉ việc"}
                    </span>
                </td>
                <td>
                    <a href="/nhan-vien/update/${nhanVien.id}" class="btn btn-primary btn-sm">
                        <i class="fas fa-user-edit"></i> Cập nhật
                    </a>
                    <a href="/nhan-vien/delete?id=${nhanVien.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa nhân viên này?');">
                        <i class="fas fa-trash-alt"></i> Xóa
                    </a>
                    <a href="/nhan-vien/detail?id=${nhanVien.id}" class="btn btn-success btn-sm">
                        <i class="fas fa-info-circle"></i> Chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút thêm mới nhân viên -->
    <a href="/nhan-vien/add" class="btn btn-success">
        <i class="fas fa-plus"></i> Thêm mới nhân viên
    </a>
</div>

<!-- Nhúng Bootstrap JS, Popper.js, và jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
